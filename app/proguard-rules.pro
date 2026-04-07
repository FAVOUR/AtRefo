# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html
# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#
#
## Keep Hilt/Dagger generated classes
#-keep class dagger.hilt.** { *; }
#-keep class javax.inject.** { *; }
#-keep class dagger.** { *; }
#
## Keep annotations
#-keepattributes *Annotation*
#
## Retrofit
#-keep class retrofit2.** { *; }
#-keepattributes Signature
#-keepattributes Exceptions
#
#-keep interface * {
#    @retrofit2.http.* <methods>;
#}
#
#
## KotlinJsonAdapterFactory
#-keep class kotlin.Metadata { *; }
#
## If @JsonClass(generateAdapter = true)
#-keep class **JsonAdapter { *; }
#-keepclassmembers class * {
#    @com.squareup.moshi.Json <fields>;
#}
#
## Gson
#-keep class com.google.gson.** { *; }
#-keepclassmembers class * {
#    @com.google.gson.annotations.SerializedName <fields>;
#}
#
#
##Okhttp
#
#-keep class okhttp3.** { *; }
#-keep class okio.** { *; }
#
#
##Kotlin and croutines
#
#-keep class kotlin.Metadata { *; }
#-dontwarn kotlinx.coroutines.**
#
#-keep class co.tractionapp.omnipaycashier.data.source.remote.model.** { *; }
#
#
##Common android rules
#
## Keep annotations
#-keepattributes *Annotation*,InnerClasses,EnclosingMethod
#
## ViewBinding / DataBinding (if used)
#-keep class androidx.databinding.** { *; }
#
#
#-dontwarn org.conscrypt.Conscrypt$Version
#-dontwarn org.conscrypt.Conscrypt
#-dontwarn org.conscrypt.ConscryptHostnameVerifier
#-dontwarn org.openjsse.javax.net.ssl.SSLParameters
#-dontwarn org.openjsse.javax.net.ssl.SSLSocket
#-dontwarn org.openjsse.net.ssl.OpenJSSE