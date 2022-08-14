## MySQL 
For Windows, MySQL provides MSI and zip package. MSI package is not difficult to install, so we provide some guide for noninstall zip package.

1. download [MySQL 8.0 ZIP file](https://dev.mysql.com/get/Downloads/MySQL-8.0/mysql-8.0.31-winx64.zip), or choose other format from [here](https://dev.mysql.com/downloads/mysql/).
2. unzip it and cd to MySQL root directory, create a directory named 'data'
3. enter 'data' directory, and run the following command to initialize the data directory
```shell
F:\mysql-8.0.31-winx64\data>..\bin\mysqld --initialize-insecure
```
The command creates a root user with empty password

4. execute the following command to start MySQL server
```shell
F:\mysql-8.0.31-winx64\data>..\bin\mysqld
```

5. enter MySQL console and change the password of root user
```shell
C:\Users\admin>mysql -uroot
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 8
Server version: 8.0.31 MySQL Community Server - GPL

Copyright (c) 2000, 2022, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> ALTER user 'root'@'localhost' IDENTIFIED BY 'root';
Query OK, 0 rows affected (0.01 sec)
```

For more information about noninstall zip archive, please see [here](https://dev.mysql.com/doc/refman/8.0/en/windows-install-archive.html).