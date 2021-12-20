package com.example.datastoresample.protodatastore.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.example.datastoresample.ProfilePreferences
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesSerializer : Serializer<ProfilePreferences> {

    override val defaultValue: ProfilePreferences = ProfilePreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProfilePreferences {
        try {
            return ProfilePreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: ProfilePreferences, output: OutputStream) = t.writeTo(output)
}