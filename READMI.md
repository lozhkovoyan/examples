
DELEGATE – ентити сажаем не него.

ID INTEGER DEFAULT DELEGATE_SQ.nextval NOT NULL,
TYPE varchar2(30 BYTE) NOT NULL,
VALUE_NAME_FIRM varchar2(30 BYTE),
VALUE_INSTEAD_NAME_FIRM varchar2(30 BYTE),
DT_BEG DATE NOT NULL ENABLE,
DT_END DATE NOT NULL ENABLE,
IS_NOW_ACTIVE CHAR(1 BYTE) DEFAULT 'Y',
USER_CREATE VARCHAR2(255 BYTE) DEFAULT USER NOT NULL,
DATA_CREATE DATE DEFAULT SYSDATE NOT NULL

Описание полей:
ID  'Идентификатор записи';
TYPE  'Тип записи (FIRST|SECOND) ';
VALUE_NAME_FIRM  ' (TYPE) Название фирмы, сдающей отчет';
VALUE_INSTEAD_NAME_FIRM  'Значения показателя (TYPE) фирмы, за которую можно сдавать отчет';
DT_BEG  'Дата начала действия записи';
DT_END  'Дата окончания действия записи';
IS_NOW_ACTIVE  'Активность записи Y/N';
USER_CREATE  'User, создавший/отредактировавший запись';
DATA_CREATE  'Дата создания/редактирования записи';


1)

Create endpoint -
С фронта придёт всё кроме ID и DATA_CREATE. ID не заполняем – оракл сам его заполнит при инсерте. DATA_CREATE – заполняем в сервисе.

Read endpoint – Отдаём всё на фронт с пагинацией. Предусмотреть фильтр по VALUE_FIRM_NAME.

Update endpoint
С фронта придёт всё кроме DATA_CREATE. Обновляем по ID, DATA_CREATE – не трогаем.
/*

3) Сделать проверку на дубликат. Создание ровно такой же записи (цифры/дата/время)
или создание записи с одинаковыми реквизитами, но которая пересекается с
периодом начала/окончания уже существующей нельзя добавлять.
Выдаем сообщение с ошибкой создания "Данная запись уже существует" - для абсолютно идентичных данных.

1. Цифры, дата и время совпадают
2. Реквизиты и дата начала ИЛИ реквизиты и дата окончания

4) Добавить кнопку удаления(front), но Должно быть удаление записи из формы и соответственно из БД

*/

GenerationTarget encountered exception accepting command : Error executing DDL "create table delegates (id number(19,0) generated as identity, type varchar2(255 char), data_create timestamp, dt_beg timestamp, dt_end timestamp, is_now_active char(1 char), user_create varchar2(255 char), value_name_firm varchar2(255 char), value_instead_name_firm varchar2(255 char), primary key (id))" via JDBC Statement

org.hibernate.tool.schema.spi.CommandAcceptanceException: Error executing DDL "create table delegates (id number(19,0) generated as identity, type varchar2(255 char), data_create timestamp, dt_beg timestamp, dt_end timestamp, is_now_active char(1 char), user_create varchar2(255 char), value_name_firm varchar2(255 char), value_instead_name_firm varchar2(255 char), primary key (id))" via JDBC Statement
at org.hibernate.tool.schema.internal.exec.GenerationTargetToDatabase.accept(GenerationTargetToDatabase.java:67) ~[hibernate-core-5.4.29.Final.jar!/:5.4.29.Final]
at org.hibernate.tool.schema.internal.AbstractSchemaMigrator.applySqlString(AbstractSchemaMigrator.java:559) ~[hibernate-core-5.4.29.Final.jar!/:5.4.29.Final]
at org.hibernate.tool.schema.internal.AbstractSchemaMigrator.applySqlStrings(AbstractSchemaMigrator.java:504) ~[hibernate-core-5.4.29.Final.jar!/:5.4.29.Final]
at org.hibernate.tool.schema.internal.AbstractSchemaMigrator.createTable(AbstractSchemaMigrator.java:277) ~[hibernate-core-5.4.29.Final.jar!/:5.4.29.Final]
at org.hibernate.tool.schema.internal.GroupedSchemaMigratorImpl.performTablesMigration(GroupedSchemaMigratorImpl.java:71) ~[hibernate-core-5.4.29.Final.jar!/:5.4.29.Final]
at org.hibernate.tool.schema.internal.AbstractSchemaMigrator.performMigration(AbstractSchemaMigrator.java:207) ~[hibernate-core-5.4.29.Final.jar!/:5.4.29.Final]
at org.hibernate.tool.schema.internal.AbstractSchemaMigrator.doMigration(AbstractSchemaMigrator.java:114) ~[hibernate-core-5.4.29.Final.jar!/:5.4.29.Final]
at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.performDatabaseAction(SchemaManagementToolCoordinator.java:184) ~[hibernate-core-5.4.29.Final.jar!/:5.4.29.Final]
at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.process(SchemaManagementToolCoordinator.java:73) ~[hibernate-core-5.4.29.Final.jar!/:5.4.29.Final]
at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:318) ~[hibernate-core-5.4.29.Final.jar!/:5.4.29.Final]
at org.hibernate.boot.internal.SessionFactoryBuilderImpl.build(SessionFactoryBuilderImpl.java:468) ~[hibernate-core-5.4.29.Final.jar!/:5.4.29.Final]
at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1259) ~[hibernate-core-5.4.29.Final.jar!/:5.4.29.Final]
at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:58) ~[spring-orm-5.3.5.jar!/:5.3.5]
at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:365) ~[spring-orm-5.3.5.jar!/:5.3.5]
at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:409) ~[spring-orm-5.3.5.jar!/:5.3.5]
at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:396) ~[spring-orm-5.3.5.jar!/:5.3.5]
at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:341) ~[spring-orm-5.3.5.jar!/:5.3.5]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1845) ~[spring-beans-5.3.5.jar!/:5.3.5]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1782) ~[spring-beans-5.3.5.jar!/:5.3.5]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:602) ~[spring-beans-5.3.5.jar!/:5.3.5]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:524) ~[spring-beans-5.3.5.jar!/:5.3.5]
at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:335) ~[spring-beans-5.3.5.jar!/:5.3.5]
at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-5.3.5.jar!/:5.3.5]
at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:333) ~[spring-beans-5.3.5.jar!/:5.3.5]
at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208) ~[spring-beans-5.3.5.jar!/:5.3.5]
at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154) ~[spring-context-5.3.5.jar!/:5.3.5]
at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:908) ~[spring-context-5.3.5.jar!/:5.3.5]
at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:583) ~[spring-context-5.3.5.jar!/:5.3.5]
at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:144) ~[spring-boot-2.4.4.jar!/:2.4.4]
at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:769) ~[spring-boot-2.4.4.jar!/:2.4.4]
at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:761) ~[spring-boot-2.4.4.jar!/:2.4.4]
at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:426) ~[spring-boot-2.4.4.jar!/:2.4.4]
at org.springframework.boot.SpringApplication.run(SpringApplication.java:326) ~[spring-boot-2.4.4.jar!/:2.4.4]
at org.springframework.boot.SpringApplication.run(SpringApplication.java:1313) ~[spring-boot-2.4.4.jar!/:2.4.4]
at org.springframework.boot.SpringApplication.run(SpringApplication.java:1302) ~[spring-boot-2.4.4.jar!/:2.4.4]
at ru.fssp.odpea.OdpeaApplication.main(OdpeaApplication.java:9) ~[classes!/:0.0.1-SNAPSHOT]
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
at org.springframework.boot.loader.MainMethodRunner.run(MainMethodRunner.java:49) ~[odpea-0.0.1-SNAPSHOT.jar:0.0.1-SNAPSHOT]
at org.springframework.boot.loader.Launcher.launch(Launcher.java:107) ~[odpea-0.0.1-SNAPSHOT..jar:0.0.1-SNAPSHOT]
at org.springframework.boot.loader.Launcher.launch(Launcher.java:58) ~[odpea-0.0.1-SNAPSHOT.jar:0.0.1-SNAPSHOT]
at org.springframework.boot.loader.JarLauncher.main(JarLauncher.java:88) ~[odpea-0.0.1-SNAPSHOT.jar:0.0.1-SNAPSHOT]
Caused by: java.sql.SQLSyntaxErrorException: ORA-01031: insufficient privileges

	at oracle.jdbc.driver.T4CTTIoer11.processError(T4CTTIoer11.java:509) ~[ojdbc8-19.3.0.0.jar!/:19.3.0.0.0]
	at oracle.jdbc.driver.T4CTTIoer11.processError(T4CTTIoer11.java:461) ~[ojdbc8-19.3.0.0.jar!/:19.3.0.0.0]
	at oracle.jdbc.driver.T4C8Oall.processError(T4C8Oall.java:1104) ~[ojdbc8-19.3.0.0.jar!/:19.3.0.0.0]
	at oracle.jdbc.driver.T4CTTIfun.receive(T4CTTIfun.java:550) ~[ojdbc8-19.3.0.0.jar!/:19.3.0.0.0]
	at oracle.jdbc.driver.T4CTTIfun.doRPC(T4CTTIfun.java:268) ~[ojdbc8-19.3.0.0.jar!/:19.3.0.0.0]
	at oracle.jdbc.driver.T4C8Oall.doOALL(T4C8Oall.java:655) ~[ojdbc8-19.3.0.0.jar!/:19.3.0.0.0]
	at oracle.jdbc.driver.T4CStatement.doOall8(T4CStatement.java:229) ~[ojdbc8-19.3.0.0.jar!/:19.3.0.0.0]
	at oracle.jdbc.driver.T4CStatement.doOall8(T4CStatement.java:41) ~[ojdbc8-19.3.0.0.jar!/:19.3.0.0.0]
	at oracle.jdbc.driver.T4CStatement.executeForRows(T4CStatement.java:928) ~[ojdbc8-19.3.0.0.jar!/:19.3.0.0.0]
	at oracle.jdbc.driver.OracleStatement.doExecuteWithTimeout(OracleStatement.java:1205) ~[ojdbc8-19.3.0.0.jar!/:19.3.0.0.0]
	at oracle.jdbc.driver.OracleStatement.executeInternal(OracleStatement.java:1823) ~[ojdbc8-19.3.0.0.jar!/:19.3.0.0.0]
	at oracle.jdbc.driver.OracleStatement.execute(OracleStatement.java:1778) ~[ojdbc8-19.3.0.0.jar!/:19.3.0.0.0]
	at oracle.jdbc.driver.OracleStatementWrapper.execute(OracleStatementWrapper.java:303) ~[ojdbc8-19.3.0.0.jar!/:19.3.0.0.0]
	at com.zaxxer.hikari.pool.ProxyStatement.execute(ProxyStatement.java:95) ~[HikariCP-3.4.5.jar!/:na]
	at com.zaxxer.hikari.pool.HikariProxyStatement.execute(HikariProxyStatement.java) ~[HikariCP-3.4.5.jar!/:na]
	at org.hibernate.tool.schema.internal.exec.GenerationTargetToDatabase.accept(GenerationTargetToDatabase.java:54) ~[hibernate-core-5.4.29.Final.jar!/:5.4.29.Final]
	... 43 common frames omitted
Caused by: oracle.jdbc.OracleDatabaseException: ORA-01031: insufficient privileges

	at oracle.jdbc.driver.T4CTTIoer11.processError(T4CTTIoer11.java:513) ~[ojdbc8-19.3.0.0.jar!/:19.3.0.0.0]
	... 58 common frames omitted

