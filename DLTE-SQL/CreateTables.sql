--BANK TABLE--
create table bank(bank_id int not null, bank_name varchar(255) not null,bank_address varchar(255) not null);
alter table bank add constraint bank_seq primary key(bank_id);
create sequence bank_seq start with 1001 increment by 1;


 
--BRANCH TABLE--
create table branch(branch_id int not null, branch_name varchar(255) not null, branch_location varchar(255) not null, branch_address varchar(255) not null, branch_ifsc varchar(255) not null, bank_id int not null);
create sequence branch_id_seq start with 501 increment by 1;
alter table branch add constraint branch_id_seq primary key(branch_id);
alter table branch add foreign key(bank_id) references bank(bank_id);
describe branch;

 
--CUSTOMER TABLE--
create table customer(customer_id int not null, customer_name varchar(255) not null,customer_address varchar(255) not null, customer_status varchar(255) not null,customer_contact int not null,failed_attempts int);
create sequence customer_seq start with 901 increment by 1;
alter table customer_ add constraint customer_seq primary key(customer_id);



 
--PROFILE TABLE--
create table profile(profile_id int not null, profile_pan varchar(255) not null, profile_address varchar(255) not null, profile_status varchar(255) not null, customer_id int not null);
create sequence profile_seq start with 801 increment by 1;
alter table profile add constraint profile_seq primary key(profile_id);
alter table profile add foreign key(customer_id) references customer(customer_id);

 
--ACCOUNT TABLE--
create table account(account_id int  not null, account_name varchar(255) not null,account_number int not null, account_bal float not null,account_status varchar(255) not null,customer_id int not null,branch_id int not null);
create sequence account_seq start with 201 increment by 1; 
alter table account add constraint account_seq primary key(account_id);
alter table account add foreign key(customer_id) references customer(customer_id);
alter table account add foreign key(branch_id) references branch(branch_id);
alter table account modify (account_number unique);

 
--LOAN TABLE--
create table loan(loan_id int not null, loan_type varchar(255) not null, loan_name varchar(255) not null,loan_decr varchar(255) not null,loan_amount int not null,loan_emi float not null,customer_id int not null);
create sequence loan_applicaton_seq start with 601 increment by 1;
alter table loan add constraint loan_seq primary key(loan_id);
alter table loan add foreign key(customer_id) references customer(customer_id);
alter table loan add foreign key(loan_scheme_id) references loan_scheme(loan_scheme_id);

 
--TRANSACTION TABLE--
create table transactions(txn_id int not null,txn_type varchar(255) not null,txn_from varchar(255) not null,txn_to varchar(255) not null,txn_amount int not null,txn_status varchar(255)not null,txn_date date not null,account_id int not null);
create sequence txn_seq start with 301 increment by 1;
alter table transactions add constraint txn_seq primary key(txn_id);
alter table transaction add foreign key(txn_from) references account(account_number);
alter table transaction add foreign key(txn_to) references account(account_number);

 
--ROLE TABLE--
create table role(role_id int not null,role_name varchar(255)not null,role_decs varchar(255)not null,role_status varchar(255)not null,branch_id int not null);
create sequence role_seq start with 55 increment by 1;
alter table role add constraint role_seq primary key(role_id);
alter table role add foreign key(branch_id) references branch(branch_id);

 
--LOAN SCHEME TABLE--
create table loan_scheme(loan_scheme_id int not null, loan_scheme_type varchar(255) not null, loan_scheme_name varchar(255) not null,loan_scheme_decr varchar(255) not null,loan_scheme_duration int not null);
create sequence admin_loan_project_seq start with 701 increment by 1;
alter table loan_scheme add constraint loanscheme_seq primary key(loan_id);

 
--PAYEE TABLE--
create table payee(payee_id int not null, payee_name varchar(255) not null,payee_account_number int not null,customer_id int not null);
create sequence payee_seq start with 101 increment by 1;
alter table payee add constraint payee_seq primary key(payee_id);
alter table payee add foreign key(customer_id) references customer(customer_id);
alter table payee add foreign key(payee_account_number) references account(account_number);

 
