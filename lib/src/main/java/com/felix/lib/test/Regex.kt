package com.felix.lib.test

import java.util.regex.Pattern

class Regex {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Pattern.compile("([\\w]+,){2}[\\w]+(,[\\w]+)*").let {
                println(it.matcher("s234df,sfdfg,sdfsd,sdfsd,sdfs").matches())
                println(it.matcher("sdf,s234fdfg,sdfsd").matches())
                println(it.matcher("sdf,s234fdfg").matches())
                println(it.matcher("sdf,sfdfg,sdf234sd,234sdf").matches())
            }
            println("========")
            Pattern.compile("([\\w]+,){2}").let {
                println(it.matcher("sdf,sfd,").matches())
                println(it.matcher("sdfsfd,").matches())
                println(it.matcher("sdfsfd").matches())
            }
        }
    }
}