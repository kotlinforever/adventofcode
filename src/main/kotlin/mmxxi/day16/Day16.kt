package mmxxi.day16

import utils.FileHandler
import java.io.File

fun main() {
    Day16().main()
}

class Day16 {

    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/mmxxi/day16/input.txt"))
    private var inputs = arrayListOf<String>(
//        "D2FE28",
//        "38006F45291200",
//        "EE00D40C823060",
//        "0054FEC8C54DC02295D5AE9B243D2F4FEA154493A43E0E60084E61CE802419A95E38958DE4F100B9708300466AB2AB7D80291DA471EB9110010328F820084D5742D2C8E600AC8DF3DBD486C010999B44CCDBD401C9BBCE3FD3DCA624652C400007FC97B113B8C4600A6002A33907E9C83ECB4F709FD51400B3002C4009202E9D00AF260290D400D70038400E7003C400A201B01400B401609C008201115003915002D002525003A6EB49C751ED114C013865800BFCA234E677512952E20040649A26DFA1C90087D600A8803F0CA1AC1F00042A3E41F8D31EE7C8D800FD97E43CCE401A9E802D377B5B751A95BCD3E574124017CF00341353E672A32E2D2356B9EE79088032AF005E7E8F33F47F95EC29AD3018038000864658471280010C8FD1D63C080390E61D44600092645366202933C9FA2F460095006E40008742A8E70F80010F8DF0AA264B331004C52B647D004E6EEF534C8600BCC93E802D38B5311AC7E7B02D804629DD034DFBB1E2D4E2ACBDE9F9FF8ED2F10099DE828803C7C0068E7B9A7D9EE69F263B7D427541200806582E49725CFA64240050A20043E25C148CC600F45C8E717C8010E84506E1F18023600A4D934DC379B9EC96B242402504A027006E200085C6B8D51200010F89913629A805925FBD3322191A1C45A9EACB4733FBC5631A210805315A7E3BC324BCE8573ACF3222600BCD6B3997E7430F004E37CED091401293BEAC2D138402496508873967A840E00E41E99DE6B9D3CCB5E3F9A69802B2368E7558056802E200D4458AF1180010A82B1520DB80212588014C009803B2A3134DD32706009498C600664200F4558630F840188E11EE3B200C292B59124AFF9AE6775ED8BE73D4FEEFFAD4CE7E72FFBB7BB49005FB3BEBFA84140096CD5FEDF048C011B004A5B327F96CC9E653C9060174EA0CF15CA0E4D044F9E4B6258A5065400D9B68",
//        "8A004A801A8002F478",
        "620080001611562C8802118E34",
//    "C0015000016115A2E0802F182340"
    )

    fun main() {
//        test()
        parse("0101010110001011001000100000000010000100011000111000110100")
    }

    fun test() {
        for (input in inputs) {
            println("\n$input")
            var bin = ""
            for (c in input) {
                bin += hexToBinary(c.toString())
            }
            parse(bin)
        }
    }

    private fun parse(bin: String) {
        println("\n***************** New package *****************")
        println(bin)
        val packetVersion = bin.substring(0, 3).toInt(2)
        println("Packet version...: $packetVersion")

        // 4 : a literal value
        // not 4 : an operator
        val packetTypeId = bin.substring(3, 6).toInt(2)
        println("Packet type ID...: $packetTypeId")

        if (packetTypeId == 4) {
            println("packetTypeId == 4")

            var idx = 6
            var total = ""
            var firstBit = 0
            do {
                firstBit = bin.substring(idx++, idx).toInt()
                total += bin.substring(idx, idx + 4)
                idx += 4
            } while (firstBit == 1)
            println("total..: $total")
            println("total..: ${total.toInt(2)}")
        } else {
            // 0 : 15-bit number
            // 1 : 11-bit number
            val length = bin.substring(6, 7).toInt(2)
            println("length...: $length")

            if (length == 0) {
                val subPacketsLength = bin.substring(7, 22).toInt(2)
                println("subPacketsLength...: $subPacketsLength")

                val firstSubPacket = bin.substring(22, 33)
                println("firstSubPacket...: $firstSubPacket")
                parse(firstSubPacket)

                val lastSubPacket = bin.substring(33, 33 + subPacketsLength - 11)
                println("lastSubPacket...: $lastSubPacket")
                parse(lastSubPacket)
            } else {
                val noOfPackages = bin.substring(7, 18).toInt(2)
                println("noOfPackages...: $noOfPackages")
                var idx = 18

                if (noOfPackages <= 1) {
                    val subPacket = bin.substring(idx)
                    parse(subPacket)
                } else {
                    for (i in 1..noOfPackages) {
                        val subPacket = bin.substring(idx, idx + 11)
                        idx += 11
                        println("subPacket...: $subPacket")
                        parse(subPacket)
                    }
                }
            }
        }
    }

    private fun hexToBinary(hex: String): String {
        val i = hex.toInt(16)
        var b: String = Integer.toBinaryString(i)
        for (x in b.length..3) {
            b = "0$b"
        }
        return b
    }

    fun convertBinaryToDecimal(aNum: Long): Int {
        var num = aNum
        var decimalNumber = 0
        var i = 0
        var remainder: Long

        while (num.toInt() != 0) {
            remainder = num % 10
            num /= 10
            decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
            ++i
        }
        return decimalNumber
    }
}
