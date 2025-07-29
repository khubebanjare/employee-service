 DROP TABLE IF EXISTS employee_tab;
 create table employee_tab (
        emp_id bigint not null,
        first_name varchar(255),
        last_name varchar(255),
        age integer,
        sal float(53),
        primary key (emp_id)
    )