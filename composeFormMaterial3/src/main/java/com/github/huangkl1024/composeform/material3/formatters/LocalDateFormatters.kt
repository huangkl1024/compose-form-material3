package com.github.huangkl1024.composeform.material3.formatters

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern


const val shortFormatPattern = "yy/MM/dd"

@OptIn(FormatStringsInDatetimeFormats::class)
val shortDateFormat = LocalDate.Format {
    byUnicodePattern(shortFormatPattern)
}

fun dateShort(r: LocalDate?): String {
    return if (r != null) shortDateFormat.format(r) else ""
}

const val longFormatPattern = "yyyy-MM-dd"

@OptIn(FormatStringsInDatetimeFormats::class)
val longDateFormat = LocalDate.Format {
    byUnicodePattern(longFormatPattern)
}

fun dateLong(r: LocalDate?): String {
    return if (r != null) longDateFormat.format(r) else ""
}