#include <jni.h>


int x_local;

// Be carefull with "_MainActivity_" - use your real activity name!
// Not all function tested in this project tested see MainActivity.java

// bare C

JNIEXPORT void JNICALL Java_org_testjni_android_MainActivity_cVsomeFunction(JNIEnv * env, jobject obj, jint x)
{
    int x_local = 2*x;
}

JNIEXPORT int JNICALL Java_org_testjni_android_MainActivity_cIsomeFunction(JNIEnv * env, jobject obj, jint x) // tested
{
    return 3*x;
}

JNIEXPORT double JNICALL Java_org_testjni_android_MainActivity_DsomeFunction(JNIEnv * env, jobject obj, jdouble x)
{
    double xx = x*2.0;
    return xx;
}

JNIEXPORT int JNICALL Java_org_testjni_android_MainActivity_cTestInt(JNIEnv * env, jobject obj, jint i_in)
{
    int i=i_in;
    return 5*i;
}

// C calls pascal
extern void pas_void_void();
JNIEXPORT void JNICALL Java_org_testjni_android_MainActivity_cVoidVoid(JNIEnv * env, jobject obj) // tested
{
    pas_void_void();
}

extern long int pas_double2int(double x);
JNIEXPORT long int JNICALL Java_org_testjni_android_MainActivity_cDouble2Int(JNIEnv * env, jobject obj, jdouble x_in) // tested
{
    double x = (double) x_in;
    int i= pas_double2int(x_in);
    return i;
}

extern long int pas_intvardouble(long int i, double*); //ok
JNIEXPORT int JNICALL Java_org_testjni_android_MainActivity_cIntVarDouble(JNIEnv * env, jobject obj, jint i_in, jdouble x_in)
{
    double xx;
    int i=i_in;
    i=pas_intvardouble(i, &xx);
    x_in = xx;
    return i;
}

extern double pas_int2double(long int i);
JNIEXPORT jdouble JNICALL Java_org_testjni_android_MainActivity_cInt2Double(
        JNIEnv * env, jobject obj, jint i_in) {
    double x_out = pas_int2double(i_in);
    return x_out;
}

extern double pas_double2double(double x);
JNIEXPORT jdouble JNICALL Java_org_testjni_android_MainActivity_cDouble2Double(
        JNIEnv * env, jobject obj, jdouble x_in) {
    double x_out = pas_double2double(x_in);
    return x_out;
}

extern long int pas_loadbinlib(int i);
JNIEXPORT int JNICALL Java_org_testjni_android_MainActivity_cLoadBinLib(JNIEnv * env, jobject obj, jint i_in)
{
    int i = pas_loadbinlib(i_in);
    return i;
}

extern long int pas_intarraymult(int iMultiplier, jint* IntArray0); // tested
JNIEXPORT int JNICALL Java_org_testjni_android_MainActivity_cIntArrayMultiply(JNIEnv * env, jobject obj, jint iMultiplier,
                                                                              jintArray iArrayToBeMultiplied)
{
    jint *piArrayToBeMultiplied = (*env)->GetIntArrayElements(env, iArrayToBeMultiplied, 0);
    int i=iMultiplier;
    i = pas_intarraymult(i, piArrayToBeMultiplied );
    return i;
}



extern long int pas_doublearraymult(double dMultiplier, jdouble* DoubleArray0);

JNIEXPORT int JNICALL
Java_org_testjni_android_MainActivity_cDoubleArrayMultiply(JNIEnv *env, jobject obj,
                                                           jdouble dMultiplier,
                                                           jdoubleArray dArrayToBeMultiplied)
//		jfloatArray fArrayToBeMultiplied)
{
    jdouble *pfArrayToBeMultiplied = (*env)->GetDoubleArrayElements(env, dArrayToBeMultiplied, 0);
//	jfloat *pfArrayToBeMultiplied = (*env)->GetFloatArrayElements(env, fArrayToBeMultiplied, 0);
    double d = dMultiplier;
    long int i = pas_doublearraymult(dMultiplier, pfArrayToBeMultiplied);
    return i;
}


