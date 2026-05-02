package com.fav.atrefo

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  // App-wide singleton scope
object FirebaseModule {

    /**
     * Provides the FirebaseApp instance.
     * FirebaseApp is initialized automatically via google-services plugin,
     * but we expose it through Hilt for completeness or testing overrides.
     */
    @Provides
    @Singleton
    fun provideFirebaseApp(
        @ApplicationContext context: Context
    ): FirebaseApp {
        return FirebaseApp.getInstance()
        // Or: FirebaseApp.initializeApp(context) ?: FirebaseApp.getInstance()
    }

    /**
     * Provides the FirebaseDatabase instance (Realtime Database).
     * Scoped as Singleton — one instance for the entire app lifetime.
     */
    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
        // For a specific DB URL:
        // return FirebaseDatabase.getInstance("https://your-project-default-rtdb.firebaseio.com/")
    }

    /**
     * Provides a specific DatabaseReference (root or named node).
     * Useful when you want to inject a pre-configured reference directly.
     */
    @Provides
    @Singleton
    fun provideRootDatabaseReference(
        database: FirebaseDatabase
    ): DatabaseReference {
        return database.reference  // root "/"
        // Or a specific node: database.getReference("users")
    }
}