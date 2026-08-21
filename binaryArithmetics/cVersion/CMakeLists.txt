cmake_minimum_required(VERSION 4.3.1)
project(BinaryArithmeticsTest C)

set(CMAKE_C_STANDARD 11)
set(CMAKE_C_STANDARD_REQUIRED ON)

set(SHARED_DIR "${CMAKE_CURRENT_SOURCE_DIR}/../../shared")

include_directories(
    ${CMAKE_CURRENT_SOURCE_DIR}/..
    ${CMAKE_CURRENT_SOURCE_DIR}
    ${CMAKE_CURRENT_SOURCE_DIR}/binary
    ${CMAKE_CURRENT_SOURCE_DIR}/bitwise
    ${CMAKE_CURRENT_SOURCE_DIR}/arithmetics
    ${SHARED_DIR}/cHeadersg
)

set(SOURCES
    test.c
    binary/binary.c
    bitwise/bitwiseLogic.c
    arithmetics/arithmetics.c
    ${SHARED_DIR}/cHeaders/Exposure.c
)

add_executable(test ${SOURCES})