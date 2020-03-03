-keepattributes *Annotation*,EnclosingMethod,Signature
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembernames enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
-dontwarn java.lang.invoke.*
-dontwarn javax.**

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}
