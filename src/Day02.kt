enum class EMoveType {
    UP,
    DOWN,
    FORWARD,
}

class Move(
    val moveType: EMoveType,
    val distance: Int,
)

open class Submarine(
    var hPos: Int,
    var depth: Int,
) {
    open fun move(move: Move) {
        when (move.moveType) {
            EMoveType.FORWARD -> {
                hPos += move.distance
            }
            EMoveType.UP -> {
                depth -= move.distance
            }
            EMoveType.DOWN -> {
                depth += move.distance
            }
        }
    }
}

class SubmarinePart2(
    hPos: Int,
    depth: Int,
    var aim: Int,
) : Submarine(
    hPos,
    depth
) {
    override fun move(move: Move) {
        when (move.moveType) {
            EMoveType.FORWARD -> {
                hPos += move.distance
                depth += aim * move.distance
            }
            EMoveType.UP -> {
                aim -= move.distance
            }
            EMoveType.DOWN -> {
                aim += move.distance
            }
        }
    }
}

fun main() {

    fun createMoveList(): List<Move> {
        return readInput("day2-submarineMoves").map { line: String ->
            val lineValues = line.split(" ")
            val moveType = EMoveType.valueOf(lineValues[0].uppercase())
            val distance = lineValues[1].toInt()
            Move(moveType, distance)
        }
    }

    val moveList = createMoveList()

    fun <T: Submarine> moveSubmarine(moves: List<Move>, submarine: T): T {
        for (move in moves) {
            submarine.move(move)
        }
        return submarine
    }

    check("forward" == EMoveType.FORWARD.toString().lowercase())
    check("up" == EMoveType.UP.toString().lowercase())
    check("down" == EMoveType.DOWN.toString().lowercase())
    check(EMoveType.valueOf("forward".uppercase()) == EMoveType.FORWARD)
    check(EMoveType.valueOf("up".uppercase()) == EMoveType.UP)
    check(EMoveType.valueOf("down".uppercase()) == EMoveType.DOWN)

    check(EMoveType.FORWARD == moveList[0].moveType)
    check(7 == moveList[0].distance)

    val submarinePart1 = moveSubmarine(
        moveList, Submarine(0, 0)
    )
    println("Submarine Values = horizontal position: ${submarinePart1.hPos}; depth: ${submarinePart1.depth}")
    println("Solution of Part1 = " + submarinePart1.hPos * submarinePart1.depth)

    val submarinePart2 = moveSubmarine(
        moveList, SubmarinePart2(0, 0, 0)
    )
    println("Submarine Values = horizontal position: ${submarinePart2.hPos}; depth: ${submarinePart2.depth}; aim: ${submarinePart2.aim}")
    println("Solution of Part2 = " + submarinePart2.hPos * submarinePart2.depth)


}

