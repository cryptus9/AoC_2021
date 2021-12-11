fun main() {

    fun createDiagnosticsList(): Array<IntArray> {
        val diagnosticsReport = readInput("day3-diagnosticsReport")
        val entryLength = diagnosticsReport[0].length
        val reportArray: Array<IntArray> = Array(diagnosticsReport.size) { IntArray(entryLength) }
        diagnosticsReport.forEachIndexed { reportIndex, reportEntry ->
            reportEntry.toCharArray().forEachIndexed { charIndex, char ->
                reportArray[reportIndex][charIndex] = char.toString().toInt()
            }
        }
        return reportArray
    }

    fun getBitByProminence(bitsByIndex: IntArray, mostProminent: Boolean): Int {
        val bitCount = bitsByIndex.groupBy { entry -> entry }
        val amount1 = bitCount.getValue(1).size
        val amount0 = bitCount.getValue(0).size
        val result: Int = if (mostProminent) {
            when (amount1 >= amount0) {
                true -> 1
                false -> 0
            }
        } else {
            when (amount1 >= amount0) {
                true -> 0
                false -> 1
            }
        }

        return result
    }

    fun getMostProminentBitOfPosition(reportLog: Array<IntArray>, charIndex: Int): Int {
        val bitsByIndex = IntArray(reportLog.size) { reportLog[it][charIndex] }
        return getBitByProminence(bitsByIndex, true)
    }

    fun getLeastProminentBitOfPosition(reportLog: Array<IntArray>, charIndex: Int): Int {
        val bitsByIndex = IntArray(reportLog.size) { reportLog[it][charIndex] }
        return getBitByProminence(bitsByIndex, false)
    }

    fun calculateGammaRate(reportLog: Array<IntArray>): IntArray {
        val resultLength = reportLog[0].size
        val result = IntArray(resultLength)
        for (charIndex in 0 until resultLength) {
            result[charIndex] = getMostProminentBitOfPosition(reportLog, charIndex)
        }
        return result
    }

    fun calculateEpsilonRate(gammaRate: IntArray): IntArray {
        return IntArray(gammaRate.size) {
            if (gammaRate[it] == 1) {
                0
            } else {
                1
            }
        }
    }

    val diagnosticsReport = createDiagnosticsList()

    check("011111111100" == diagnosticsReport[0].joinToString(separator = ""))
    check("0" == diagnosticsReport[0][0].toString())
    check("0" == diagnosticsReport[0][10].toString())
    check("0" == diagnosticsReport[0][11].toString())
    check("1" == diagnosticsReport[0][1].toString())
    check("1" == diagnosticsReport[0][6].toString())
    check("1" == diagnosticsReport[0][9].toString())
    check("110001100010" == diagnosticsReport[diagnosticsReport.size - 1].joinToString(separator = ""))

    val gammaRate = calculateGammaRate(diagnosticsReport)
    val epsilonRate = calculateEpsilonRate(gammaRate)
    val gammaInt = gammaRate.joinToString("").toInt(2)
    val epsilonInt = epsilonRate.joinToString("").toInt(2)
    val result = gammaInt * epsilonInt
    println("Gamma rate: $gammaInt")
    println("Epsilon rate: $epsilonInt")
    println("Solution of part1 = $result")


    check(654 == gammaInt)
    check(3441 == epsilonInt)

    fun calculateOxygenGeneratorRating(reportLog: Array<IntArray>, currentIndex: Int): IntArray {
        if (reportLog.size == 1) {
            return reportLog[0]
        } else {
            val prominentBit = getMostProminentBitOfPosition(reportLog, currentIndex)
            return calculateOxygenGeneratorRating(
                reportLog.filter { enty: IntArray -> prominentBit == enty[currentIndex] }.toTypedArray(),
                currentIndex + 1
            )
        }
    }

    fun calculateCO2ScrubberRating(reportLog: Array<IntArray>, currentIndex: Int): IntArray {
        if (reportLog.size == 1) {
            return reportLog[0]
        } else {
            val prominentBit = getLeastProminentBitOfPosition(reportLog, currentIndex)
            return calculateCO2ScrubberRating(
                reportLog.filter { enty: IntArray -> prominentBit == enty[currentIndex] }.toTypedArray(),
                currentIndex + 1
            )
        }
    }

    val oxygenGeneraorRating = calculateOxygenGeneratorRating(diagnosticsReport, 0).joinToString("").toInt(2)
    val co2ScrubberRating = calculateCO2ScrubberRating(diagnosticsReport, 0).joinToString("").toInt(2)
    println("oxygenGeneratorRating is: $oxygenGeneraorRating")
    println("co2ScrubberRating is: $co2ScrubberRating")
    println("Solution of Part2: " + oxygenGeneraorRating * co2ScrubberRating)
}