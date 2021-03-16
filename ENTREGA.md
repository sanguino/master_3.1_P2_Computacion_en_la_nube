# P2

## parte sin spring cloud

Los ficheros `demo.mov` y `p2.yml` son de la parte básica + del parte avanzada bucket creado por CloudFormation + limitar aplicación para que solo pueda operar sobre el bucket, sin spring cloud aws.


## parte con spring cloud aws

La demo `demo_spring_cloud.mov` y `p2_spring_cloud.yml` son de la parte basica + avanzada completa.

En la carpeta `JAVA` está el código cambiado para usar spring cloud, y el jar lo dejo subido en: `https://jar-sanguino-mca.s3.amazonaws.com/app.jar`

## DUDAS

El fichero `p2_spring_cloud.yml` arranca ya con todo, pero tengo dudas que me gustaría aclarar, ya que he tenido que añadir muchas policies que no acabo de entender la razón de tener que hacer y antes no.

En el fichero `p2_duda.yml`, es la adaptación de la primera versión + permisos para rds. Funciona todo salvo que el s3 no es legible públicamente. Y al añadir la `BucketPolicy` para permitir `getObject` desde cualquier sitio, es cuando me han empezado a fallar por falta de permisos y he tenido que añadirselos, los que tiene `S3Policy`.

También he probado a ponerle en `ManagedPolicyArns` los arn de fullaccess de RDS y S3, y con eso tampoco necesito permisos de autoscaling ni de cloudformation, aunque pierdo la limitación del bucket. Pero no se entiende la razón por la que segun ciertos permisos más restrictivos se necesiten otros.

Seguro que algo he hecho mal al final, pero no acabo de dar con la tecla.

