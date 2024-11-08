package com.github.huangkl1024.composeform.material3.formatters

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern


const val shortDateFormatPattern = "yy/MM/dd"

@OptIn(FormatStringsInDatetimeFormats::class)
val shortDateFormat = LocalDate.Format {
    byUnicodePattern(shortDateFormatPattern)
}

fun dateShort(r: LocalDate?): String {
    return if (r != null) shortDateFormat.format(r) else ""
}

const val longDateFormatPattern = "yyyy-MM-dd"

@OptIn(FormatStringsInDatetimeFormats::class)
val longDateFormat = LocalDate.Format {
    byUnicodePattern(longDateFormatPattern)
}

fun dateLong(r: LocalDate?): String {
    return if (r != null) longDateFormat.format(r) else ""
}