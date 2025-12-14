package model

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import util.ClassUtil.mapToClass
import java.io.File
import java.text.SimpleDateFormat

class ExcelParser(
    file: File,
    sheetIndex: Int = 0,
    val inHeader: Boolean = true
) {
    private val workBook = when ((file.name ?: "").uppercase().split(".").last()) {
        "XLS" -> HSSFWorkbook(file.inputStream())
        "XLSX" -> XSSFWorkbook(file.inputStream())
        else -> throw Exception("invalid file type")
    }

    val sheet: Sheet = workBook.getSheetAt(sheetIndex) ?: throw Exception("invalid sheet index")

    var startRow = 0
    var startCell = 0
    var lastRow = sheet.lastRowNum
    var lastCell = findLastCell()

    var currentRow = startRow
    var currentCell = startCell

    fun startRow(n: Int) = apply {
        startRow = n
        currentRow = n
    }

    fun startCell(n: Int) = apply {
        startCell = n
        currentCell = n
    }

    fun lastRow(n: Int) = apply { lastRow = n }

    fun lastCell(n: Int) = apply { lastCell = n }

    fun autoLastCell() = apply { lastCell = findLastCell() }

    private fun findLastCell(): Int {
        var maxCell = 0
        forEachRow { if (it.lastCellNum > maxCell) maxCell = it.lastCellNum.toInt() }
        return maxCell
    }

    val row: Row
        get() = sheet.getRow(currentRow)

    val headerRow: Row?
        get() = inHeader.takeIf { it }?.let { sheet.getRow(startRow) }

    val cell: Cell?
        get() = row.getCell(currentCell)

    val headerCell: Cell?
        get() = inHeader.takeIf { it }
            ?.let { headerRow?.getCell(currentCell) }

    val value: String?
        get() = getCellValue(cell)

    val headerValue: String?
        get() = getCellValue(headerCell)

    val nextValue: String?
        get() = nextCell().value

    val size: Int
        get() = lastRow - startRow + 1 - if (inHeader) 1 else 0

    fun nextRow(n: Int = 1): ExcelParser = apply {
        currentRow += n
        if (currentRow > lastRow) repeat(currentRow / lastRow) { currentRow -= lastRow + startRow - 1 }
    }

    fun nextCell(n: Int = 1): ExcelParser = apply {
        currentCell += n
        if (currentCell > lastCell) {
            repeat(currentCell / lastCell) {
                 currentCell -= lastCell + startCell - 1
                nextRow()
            }
        }
    }

    fun reset(): ExcelParser = apply {
        currentRow = startRow
        currentCell = startCell
    }

    fun getCellValue(cell: Cell?): String? {
        return when (cell?.cellType) {
            CellType.NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.dateCellValue)
                } else {
                    val value = cell.numericCellValue
                    when (value == value.toLong().toDouble()) {
                        true -> value.toLong().toString()
                        false -> value.toString()
                    }
                }
            }

            CellType.BOOLEAN -> cell.booleanCellValue.toString()
            CellType.BLANK -> null
            else -> cell?.stringCellValue
        }
    }

    fun forEachRow(action: (Row) -> Unit) {
        repeat(lastRow - currentRow + 1) {
            action(row)
            nextRow()
        }
    }

    fun forEachCell(action: (Cell?) -> Unit) {
        val repeat = lastCell - currentCell + 1
        repeat(repeat) {
            action(cell)
            nextCell()
            if (it + 1 == repeat) nextRow(-1)
        }
    }

    inline fun <reified T> mapping(): List<T> {
        val classList = mutableListOf<T>()
        val fieldNames = T::class.java.declaredFields.map { it.name }

        reset()
        if (inHeader) nextRow()

        forEachRow { _ ->
            val results = mutableListOf<String?>()
            forEachCell { results.add(value) }

            val fieldMap = mutableMapOf<String, String?>()
            fieldNames.mapIndexed { index, fieldName -> fieldMap[fieldName] = results.getOrNull(index) }
            classList.add(fieldMap.mapToClass<T>()!!)
        }

        return classList
    }

    fun getHeaders(): List<String> {
        reset()
        if (!inHeader) return emptyList()

        val headers = mutableListOf<String>()
        forEachCell { headerValue?.let { headers.add(it) } }
        return headers
    }

    inline fun <reified T> headersMapping(): Map<String, String> {
        val headerMap = mutableMapOf<String, String>()
        val headers = getHeaders()
        T::class.java.declaredFields.mapIndexed { index, field ->
            headerMap[field.name] = headers.getOrNull(index) ?: field.name
        }
        return headerMap
    }
}