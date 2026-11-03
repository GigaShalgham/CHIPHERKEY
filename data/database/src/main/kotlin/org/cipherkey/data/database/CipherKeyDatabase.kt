package org.cipherkey.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Local-only database.
 *
 * allowBackup is disabled at the manifest level; combined with the Keystore
 * wrapping of key material this means a device backup contains no usable key
 * material even if the database file itself is extracted.
 */
@Database(
    entities = [ContactEntity::class, SessionKeyEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class CipherKeyDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
    abstract fun sessionKeyDao(): SessionKeyDao

    companion object {
        const val NAME = "cipherkey.db"
    }
}
