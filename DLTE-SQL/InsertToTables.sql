--BANK TABLE--
insert into Bank values(bank_seq.NEXTVAL,'Unity Bank','Bangalore');

--BRANCH TABLE--
insert into Branch values(branch_seq.NEXTVAL,'Karkala','SRT Street','UNIT0089897',bank_id_seq.CURRVAL);
insert into Branch values(branch_seq.NEXTVAL,'Moodibidri','BR Road','UNIT0988987',bank_id_seq.CURRVAL);


--ROLE TABLE--
insert into role values(role_seq.NEXTVAL,'Bank Official','Resposible for managing operations','Deactive',3000);
insert into role values(role_seq.NEXTVAL,'Bank Admin','Resposible for assisting bank-official and managers in day-to-day activities','Active',3002);


--CUSTOMER TABLE--
insert into customer values(CUSTOMER_SEQ.nextval,'Amrutha','Manipal','Active',9876543456,'manvith',7477);
insert into customer values(CUSTOMER_SEQ.nextval,'Shravya','Udupi','Active',9878765456,'shravya',5656);
insert into customer values(CUSTOMER_SEQ.nextval,'Kirthan','Nitte','Deactive',9878710101,'kirthan',6476);
insert into customer values(CUSTOMER_SEQ.nextval,'Gourav','Bailoor','Deactive',9878734353,'gourav',6787);
insert into customer values(CUSTOMER_SEQ.nextval,'Manvith','Udupi','Active',8787734353,'manvith',2345);

--PAYEE TABLE--
insert into payee values(PAYEE_SEQ.nextval,'Shravya',98765432123,1);
insert into payee values(PAYEE_SEQ.nextval,'Gourav',98765430003,2);
insert into payee values(PAYEE_SEQ.nextval,'Manvith',98763450003,3);
insert into payee values(PAYEE_SEQ.nextval,'Shravya',98765432123,2);
insert into payee values(PAYEE_SEQ.nextval,'Kirthan',98765430003,1);
insert into payee values(PAYEE_SEQ.nextval,'Gourav',98763450003,3);


--PROFILE--
insert into profile values(PROFILE_ID_SEQ.nextval,'WYET637YU7',879656784321,'Active',2);
insert into profile values(PROFILE_id_SEQ.nextval,'JUYT957YU7',923456784321,'Suspended',1);
insert into profile values(PROFILE_id_SEQ.nextval,'AWED437YU7',345656784321,'Active',3);
insert into profile values(PROFILE_id_SEQ.nextval,'AGSH748U88',987654378954,'Active',4);

--ACCOUNT--
insert into account values(ACCOUNT_ID_SEQ.nextval,'savings',098775444423,10000,'Active',1,7);
insert into account values(ACCOUNT_ID_SEQ.nextval,'current',098765432123,50000,'Active',2,8);
insert into account values(ACCOUNT_ID_SEQ.nextval,'savings',132456432123,500000,'Active',2,7);
insert into account values(ACCOUNT_ID_SEQ.nextval,'current',876678444423,1000000,'Active',1,9);
insert into account values(ACCOUNT_ID_SEQ.nextval,'savings',122178444423,7000000,'Active',3,7);
insert into account values(ACCOUNT_ID_SEQ.nextval,'current',876678994423,900000,'Active',3,8);
insert into account values(ACCOUNT_ID_SEQ.nextval,'savings',765478444423,200000,'Active',4,9);
insert into account values(ACCOUNT_ID_SEQ.nextval,'current',456788444423,4000000,'Active',4,8);

--TRANSACTION--
insert into transaction values(TXN_ID_SEQ.nextval,122178444423,20000,'processing','02/Jan/2022',098775444423);
insert into transaction values(TXN_ID_SEQ.nextval,098775444423,30000,'pending','09/April/2023',122178444423);
insert into transaction values(TXN_ID_SEQ.nextval,132456432123,400000,'processing','10/Jan/2021',876678444423);
insert into transaction values(TXN_ID_SEQ.nextval,876678994423,1000,'success','12/March/2012',098775444423);
insert into transaction values(TXN_ID_SEQ.nextval,456788444423,2000,'pending','02/Nov/2002',122178444423);
insert into transaction values(TXN_ID_SEQ.nextval,122178444423,3000,'processing','14/Dec/2017',098775444423);
insert into transaction values(TXN_ID_SEQ.nextval,765478444423,6000,'success','02/Jan/2007',098775444423);
insert into transaction values(TXN_ID_SEQ.nextval,876678994423,9000,'processing','15/June/2014',456788444423);

--LOAN--
insert into loan values(loan_application_seq.NEXTVAL, 50000.00, 7800.653, 5000, 1);
insert into loan values(loan_application_seq.NEXTVAL, 100000.00, 7800.653, 5002, 1);
insert into loan values(loan_application_seq.NEXTVAL, 250000.00, 7800.653, 5003, 2);
insert into loan values(loan_application_seq.NEXTVAL, 64000.00, 7800.653, 5001, 4);
insert into loan values(loan_application_seq.NEXTVAL, 70000.00, 7800.653, 5003, 3);

--LOAN SCHEME--
insert into loanscheme values(loanscheme_id_seq.nextval,'personal loan','TurboLoan','can be taken for one's personal reasons','15');
insert into loanscheme values(loanscheme_id_seq.nextval,'home loan','FHA Loans','for buying house or construction of home','30');
insert into loanscheme values(loanscheme_id_seq.nextval,'car loans','ICBI loans','might give instant access to further oppourtunities','8.8');
insert into loanscheme values(loanscheme_id_seq.nextval,'education  loan','RTE loan','helps people with financial problem','10');






