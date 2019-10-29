cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_C_FLAGS "-fPIC")


add_library( laz_lib SHARED IMPORTED GLOBAL)
set_property(TARGET laz_lib PROPERTY IMPORTED_LOCATION "d:/AndroidStudioProjects/testjni/app/src/main/jniLibs/armeabi-v7a/liblaz_lib.so")

add_library( # Sets the name of the library.
              cwrappas
            # Sets the library as a shared library.
              SHARED
            # Provides a relative path to your source file(s).
             cwrappas.c )

TARGET_LINK_LIBRARIES(cwrappas laz_lib)



