package com.miraeldev.impl.localUser

import androidx.datastore.core.Serializer
import com.miraeldev.api.models.LocalUserEmailDataModel
import com.miraeldev.impl.crypto.CryptoManager
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

internal class LocalUserSerializer(
    private val cryptoManager: CryptoManager
) : Serializer<LocalUserEmailDataModel> {

    override val defaultValue: LocalUserEmailDataModel
        get() = LocalUserEmailDataModel(email = "")

    override suspend fun readFrom(input: InputStream): LocalUserEmailDataModel {
        val decryptedBytes = cryptoManager.decrypt(input)
        return try {
            Json.decodeFromString(
                deserializer = LocalUserEmailDataModel.serializer(),
                string = decryptedBytes.decodeToString()
            )
        } catch (e: SerializationException) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: LocalUserEmailDataModel, output: OutputStream) {
        cryptoManager.encrypt(
            bytes = Json.encodeToString(
                serializer = LocalUserEmailDataModel.serializer(),
                value = t
            ).encodeToByteArray(),
            outputStream = output
        )
    }
}