set -eo pipefail

modules=( eureka-server gateway-server ms-product ms-order ms-auth front-server )

for module in "${modules[@]}"; do
    docker build -t "market/${module}:latest" ${module}
done