INSTALL MINIKUBE

1.
Install minikube into docker container:
minikube start --driver=docker --cpus=6 --memory=6000




DEPLOY

1.
kubectl apply -f k8s/namespace.yaml
To check:
kubectl get namespaces

2.
if env variables exist delete them:
kubectl delete secret payment-system-secrets -n payment-system
next:
(Get-Content ../.env) -join "`n" | Set-Content -NoNewline .env.clean
kubectl create secret generic payment-system-secrets --from-env-file=.env.clean -n payment-system

3.
kubectl apply -f k8s/databases/
kubectl apply -f k8s/infrastructure/
Wait until all pods are running:
kubectl get pods -n payment-system
And next:
kubectl apply -f k8s/services/
Wait until all pods are running:
kubectl get pods -n payment-system




PROVIDE INGRESS

1.
minikube addons enable ingress
Or if ingress-nginx is incompatible:
minikube addons disable ingress
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.15.1/deploy/static/provider/cloud/deploy.yaml
To check:
kubectl get pods -n ingress-nginx

2.
kubectl apply -f k8s/ingress.yaml
To check:
kubectl get ingress -n payment-system

3.
In a separate terminal window, run as administrator:
minikube tunnel

And here it is :)



PS
Delete deployments before minikube stop:
kubectl delete deployment api-gateway core-auth-service core-user-service fake-bank payment-system-order-service payment-system-payment-service payment-system-product-service payment-system-ui -n payment-system

and next time when databases and infrastructure run:
kubectl apply -f k8s/services/