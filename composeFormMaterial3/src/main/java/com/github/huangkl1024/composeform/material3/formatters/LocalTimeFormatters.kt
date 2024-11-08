package com.github.huangkl1024.composeform.material3.formatters

import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern


const val timeFormatPattern = "HH:mm"

@OptIn(FormatStringsInDatetimeFormats::class)
val timeFormat = LocalTime.Format {
    byUnicodePattern(timeFormatPattern)
}

fun time(r: LocalTime?): String {
    return if (r != null) timeFormat.format(r) else ""
}