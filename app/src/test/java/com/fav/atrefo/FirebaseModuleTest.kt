package com.fav.atrefo

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertSame
import org.junit.Test

/**
 * Unit tests for the Hilt provider bodies.
 *
 * The providers delegate to Firebase static factories, which cannot run on the JVM
 * (and cannot run on a device either — this project's google-services.json declares
 * no databaseURL, so FirebaseDatabase.getInstance() throws). Stubbing the statics
 * keeps these three lines testable without standing up a real Firebase project.
 */
class FirebaseModuleTest {

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun provideFirebaseApp_returnsTheDefaultApp() {
        val expected = mockk<FirebaseApp>()
        mockkStatic(FirebaseApp::class)
        every { FirebaseApp.getInstance() } returns expected

        val actual = FirebaseModule.provideFirebaseApp(mockk<Context>())

        assertSame(expected, actual)
        verify { FirebaseApp.getInstance() }
    }

    @Test
    fun provideFirebaseDatabase_returnsTheDefaultDatabase() {
        val expected = mockk<FirebaseDatabase>()
        mockkStatic(FirebaseDatabase::class)
        every { FirebaseDatabase.getInstance() } returns expected

        val actual = FirebaseModule.provideFirebaseDatabase()

        assertSame(expected, actual)
        verify { FirebaseDatabase.getInstance() }
    }

    @Test
    fun provideRootDatabaseReference_returnsTheDatabaseRoot() {
        val expected = mockk<DatabaseReference>()
        val database = mockk<FirebaseDatabase>()
        every { database.reference } returns expected

        val actual = FirebaseModule.provideRootDatabaseReference(database)

        assertSame(expected, actual)
    }
}
