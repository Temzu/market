@echo off

for %%i in (eureka-server gateway-server ms-product ms-order ms-auth front-server) do (
     docker build -t "market/%%i:latest" %%i
)