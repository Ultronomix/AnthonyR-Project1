/*
   Monday, September 5, 20223:46:42 PM
   User: 
   Server: EXCALION\SQLEXPRESS
   Database: P1
   Application: 
*/

/* To prevent any potential data loss issues, you should review this script in detail before running it outside the context of the database designer.*/
BEGIN TRANSACTION
SET QUOTED_IDENTIFIER ON
SET ARITHABORT ON
SET NUMERIC_ROUNDABORT OFF
SET CONCAT_NULL_YIELDS_NULL ON
SET ANSI_NULLS ON
SET ANSI_PADDING ON
SET ANSI_WARNINGS ON
COMMIT
BEGIN TRANSACTION
GO
CREATE TABLE dbo.ers_user_roles
	(
	id varchar(50) NOT NULL,
	Role varchar(50) NOT NULL
	)  ON [PRIMARY]
GO
ALTER TABLE dbo.ers_user_roles ADD CONSTRAINT
	PK_ers_user_roles PRIMARY KEY CLUSTERED 
	(
	id
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
ALTER TABLE dbo.ers_user_roles SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.ers_user_roles', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.ers_user_roles', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.ers_user_roles', 'Object', 'CONTROL') as Contr_Per 