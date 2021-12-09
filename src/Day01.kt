fun main() {

    fun readSonarLog(): List<Int> {
        val logList = mutableListOf<Int>();
        readInput("day1-sonarLog").forEach { logList.add(it.toInt()) }
        return logList;
    }

    val sonarLog = readSonarLog();

    fun getSolutionOfPart1(logList: List<Int>): Int {
        var previousLog: Int = -1
        var logWasDeeperAmount = 0
        for (currentLog in logList) {
            if (previousLog != -1) {
                if (currentLog > previousLog) {
                    logWasDeeperAmount++
                }
            }
            previousLog = currentLog;
        }
        return logWasDeeperAmount
    }
    // test if implementation meets criteria from the description, like:
    check(2000 == sonarLog.size)
    check(193 == sonarLog[0])
    check(10983 == sonarLog[1999])
    println("Solution of Part1 = " + getSolutionOfPart1(sonarLog))

    fun getListTripleSum(list: List<Int>, startIndex: Int): Int {
        return list.subList(startIndex, startIndex + 3).sum()
    }


    fun getSolutionOfPart2(input: List<Int>): Int {
        var sumDidInceaseCounter = 0;
        for (i in 0 until input.size) {
            try {
                val didSumIncrease = getListTripleSum(input, i) < getListTripleSum(input, i + 1)
                if (didSumIncrease) {
                    sumDidInceaseCounter++
                }
            }catch (e: IndexOutOfBoundsException){
                break
            }
        }
        return sumDidInceaseCounter;
    }

    check(6 == getListTripleSum(listOf(1, 2, 3, 4, 5, 6), 0))
    check(15 == getListTripleSum(listOf(1, 2, 3, 4, 5, 6), 3))
    println("Solution of Part2 = " + getSolutionOfPart2(sonarLog))

}
