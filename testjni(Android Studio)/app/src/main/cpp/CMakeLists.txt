cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_C_FLAGS "-fPIC")


add_library( laz_lib SHARED IMPORTED GLOBAL)
if( CMAKE_SIZEOF_VOID_P EQUAL 8 )
    # 64-bit lib not tested here
    # set_property(TARGET laz_lib PROPERTY IMPORTED_LOCATION "${CMAKE_SOURCE_DIR}/../jniLibs/arm64-v8a/liblaz_lib.so")
else( CMAKE_SIZEOF_VOID_P EQUAL 8 )
    # 32-bit libs
    set_property(TARGET laz_lib PROPERTY IMPORTED_LOCATION "${CMAKE_SOURCE_DIR}/../jniLibs/armeabi-v7a/liblaz_lib.so")
endif( CMAKE_SIZEOF_VOID_P EQUAL 8 )

add_library( # Sets the name of the library.
              cwrappas
            # Sets the library as a shared library.
              SHARED
            # Provides a relative path to your source file(s).
             cwrappas.c )

TARGET_LINK_LIBRARIES(cwrappas laz_lib)



