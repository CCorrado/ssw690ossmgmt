package com.ccorrads.ossp.core.injection.adapters

import android.text.TextUtils
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.joda.time.LocalDate
import java.io.IOException

class LocalDateTypeAdapter : TypeAdapter<LocalDate>() {

    @Throws(IOException::class)
    override fun read(reader: JsonReader): LocalDate? {
        var dateStr = reader.nextString()
        //Local Date adapter should chop off the time from the string.
        if (dateStr.contains("T")) {
            dateStr = dateStr.substring(0, dateStr.indexOf("T"))
        }
        return if (TextUtils.isEmpty(dateStr)) null else LocalDate.parse(dateStr)
    }

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, date: LocalDate) {
        writer.value(date.toDateTimeAtStartOfDay().toDateTimeISO().toString())
    }
}
