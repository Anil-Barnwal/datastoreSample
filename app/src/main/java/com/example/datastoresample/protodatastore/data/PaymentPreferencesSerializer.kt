package com.example.datastoresample.protodatastore.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.example.datastoresample.PaymentPreferences
import java.io.InputStream
import java.io.OutputStream

object PaymentPreferencesSerializer : Serializer<PaymentPreferences> {

    override val defaultValue: PaymentPreferences = PaymentPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): PaymentPreferences {
        try {
            return PaymentPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: PaymentPreferences, output: OutputStream) = t.writeTo(output)
}