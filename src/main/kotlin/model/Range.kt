package model

class Range<T>(start: T, end: T) where T : Comparable<T> {
    val start: T
    val end: T

    /**
     * 한 구간이 다른 구간을 완전히 포함하는지 구하는 함수
     * @param range
     * @return 포함하면 true 아니면 false
     */
    fun contains(range: Range<T>): Boolean {
        return (start <= range.start && range.end <= end)
    }

    /**
     * 한 구간이 다른 구간과 겹치는지 구하는 함수
     * 겹치는 구간의 길이가 0이어도 true
     * @param range
     * @return 겹치면 true 아니면 false
     */
    fun overlap(range: Range<T>): Boolean {
        return !(end < range.start || start > range.end)
    }

    /**
     * 한 구간이 다른 구간과 겹치는지 구하는 함수
     * 겹치는 구간의 길이가 0이면 false
     * @param range
     * @return 겹치면 true 아니면 false
     */
    fun strictlyOverlap(range: Range<T>): Boolean {
        return !(end <= range.start || start >= range.end)
    }

    /**
     * 두 구간의 교집합을 구하는 함수
     * @param range
     * @return 교집합이 존재하지 않으면 null
     */
    fun intersection(range: Range<T>): Range<T>? {
        return if (this.overlap(range))
            Range(
                if (start > range.start) start else range.start,
                if (end < range.end) end else range.end
            )
        else
            null
    }

    /**
     * 두 구간의 합집합을 구하는 함수
     * @param range
     * @return 합집합이 존재하지 않으면 null
     */
    fun union(range: Range<T>): Range<T>? {
        return if (this.overlap(range))
            Range(
                if (start < range.start) start else range.start,
                if (end > range.end) end else range.end
            )
        else
            null
    }

    init {
        this.start = if (start < end) start else end
        this.end = if (start < end) end else start
    }
}