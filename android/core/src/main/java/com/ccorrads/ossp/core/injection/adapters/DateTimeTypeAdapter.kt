package com.ccorrads.ossp.core.injection.adapters

import android.text.TextUtils
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.joda.time.DateTime
import java.io.IOException

class DateTimeTypeAdapter : TypeAdapter<DateTime>() {

    @Throws(IOException::class)
    override fun read(reader: JsonReader): DateTime? {
        val dateStr = reader.nextString()
        return if (TextUtils.isEmpty(dateStr)) null else DateTime.parse(dateStr)
    }

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, date: DateTime) {
        writer.value(date.toDateTimeISO().toString())
    }

}
