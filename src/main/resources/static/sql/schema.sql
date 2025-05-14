
    create table fabric (
        id bigint not null auto_increment,
        address varchar(255),
        description varchar(255),
        email varchar(255),
        name varchar(255),
        opening_hours varchar(255),
        phone_number varchar(255),
        point_on_the_map varchar(255),
        primary key (id)
    )

    create table feedback_on_point_from_individual (
        grade float(53) not null,
        id bigint not null auto_increment,
        person_id bigint,
        point_id bigint,
        text TEXT,
        primary key (id)
    )

    create table feedback_on_point_from_legal (
        grade float(53) not null,
        id bigint not null auto_increment,
        person_id bigint,
        point_id bigint,
        text TEXT,
        primary key (id)
    )

    create table feedback_on_product_for_individual (
        grade float(53) not null,
        id bigint not null auto_increment,
        person_id bigint,
        product_id bigint,
        text TEXT,
        primary key (id)
    )

    create table feedback_on_product_for_legal (
        grade float(53) not null,
        id bigint not null auto_increment,
        person_id bigint,
        product_id bigint,
        text TEXT,
        primary key (id)
    )

    create table individual_person (
        id bigint not null auto_increment,
        email varchar(255),
        middle_name varchar(255),
        name varchar(255),
        password varchar(255),
        phone_number varchar(255),
        surname varchar(255),
        role enum ('ADMIN','USER'),
        primary key (id)
    )

    create table legal_person (
        id bigint not null auto_increment,
        email varchar(255),
        name varchar(255),
        password varchar(255),
        phone_number varchar(255),
        primary key (id)
    )

    create table order_for_fabric (
        price integer,
        fabric_id bigint,
        id bigint not null auto_increment,
        person_id bigint,
        status enum ('CANCELLED','COMPLETED','IN_PROGRESS','ON_THE_WAY','REFUND','WAITING'),
        primary key (id)
    )

    create table order_for_individual (
        price integer,
        id bigint not null auto_increment,
        person_id bigint,
        point_id bigint,
        shipping_id bigint,
        status enum ('CANCELLED','COMPLETED','IN_PROGRESS','ON_THE_WAY','REFUND','WAITING'),
        primary key (id)
    )

    create table order_for_legal (
        price integer,
        id bigint not null auto_increment,
        person_id bigint,
        point_id bigint,
        status enum ('CANCELLED','COMPLETED','IN_PROGRESS','ON_THE_WAY','REFUND','WAITING'),
        primary key (id)
    )

    create table point_of_sale (
        id bigint not null auto_increment,
        address varchar(255),
        description varchar(255),
        email varchar(255),
        name varchar(255),
        opening_hours varchar(255),
        phone_number varchar(255),
        point_on_the_map varchar(255),
        primary key (id)
    )

    create table product_for_individual (
        id bigint not null auto_increment,
        name varchar(255),
        primary key (id)
    )

    create table product_for_legal (
        id bigint not null auto_increment,
        name varchar(255),
        primary key (id)
    )

    create table shipping_information (
        floor integer,
        price integer,
        id bigint not null auto_increment,
        address varchar(255),
        primary key (id)
    )

    create table summary_for_fabric (
        quantity integer,
        id bigint not null auto_increment,
        order_for_fabric_id bigint,
        point_id bigint,
        product_id bigint,
        primary key (id)
    )

    create table summary_for_individual (
        quantity integer,
        id bigint not null auto_increment,
        order_for_individual bigint,
        person_id bigint,
        product_id bigint,
        primary key (id)
    )

    create table summary_for_legal (
        quantity integer,
        id bigint not null auto_increment,
        order_legal_id bigint,
        person_id bigint,
        product_id bigint,
        primary key (id)
    )
    
    
    
    
    alter table feedback_on_point_from_individual 
       add constraint FK4rs8vek22belw3ggkak2srhjk 
       foreign key (person_id) 
       references individual_person (id) 
       on delete set null

    alter table feedback_on_point_from_individual 
       add constraint FK9p7un1si0bheu3gp3hx6hreas 
       foreign key (point_id) 
       references point_of_sale (id) 
       on delete cascade

    alter table feedback_on_point_from_legal 
       add constraint FKpacfo5spq97jak3fpge8rd6lp 
       foreign key (person_id) 
       references legal_person (id) 
       on delete set null

    alter table feedback_on_point_from_legal 
       add constraint FK434j9lq9oak09pk4rws0ivxo3 
       foreign key (point_id) 
       references point_of_sale (id) 
       on delete cascade

    alter table feedback_on_product_for_individual 
       add constraint FKlikhww6l978o1fjks0frkyhy8 
       foreign key (person_id) 
       references individual_person (id) 
       on delete set null

    alter table feedback_on_product_for_individual 
       add constraint FKrp0f9w7n3ddi8rxm3qgl6df47 
       foreign key (product_id) 
       references product_for_individual (id) 
       on delete cascade

    alter table feedback_on_product_for_legal 
       add constraint FKf50odx4q2288gjulmo4i49osf 
       foreign key (person_id) 
       references legal_person (id) 
       on delete set null

    alter table feedback_on_product_for_legal 
       add constraint FKlb04sb5arkqw80hra4gfb77re 
       foreign key (product_id) 
       references product_for_legal (id) 
       on delete cascade

    alter table order_for_fabric 
       add constraint FKdet85xprt0ei5hoc8lomprqpm 
       foreign key (fabric_id) 
       references fabric (id) 
       on delete cascade

    alter table order_for_fabric 
       add constraint FKpx22jvm4d2gb7nclknfwpo0kl 
       foreign key (person_id) 
       references point_of_sale (id) 
       on delete cascade

    alter table order_for_individual 
       add constraint FKltgueknmuqvwi6t814oth9yu7 
       foreign key (person_id) 
       references individual_person (id) 
       on delete cascade

    alter table order_for_individual 
       add constraint FKmfxqpa5n487eq0pk14c6kit50 
       foreign key (point_id) 
       references point_of_sale (id) 
       on delete cascade

    alter table order_for_individual 
       add constraint FKshf2slnygd7t2jgaxufsvshdp 
       foreign key (shipping_id) 
       references shipping_information (id) 
       on delete set null

    alter table order_for_legal 
       add constraint FK1mdgmr5ch7h0v3mtauplhiwqe 
       foreign key (person_id) 
       references legal_person (id) 
       on delete cascade

    alter table order_for_legal 
       add constraint FK2o1otxvimtdo0l3g4hfnv59ov 
       foreign key (point_id) 
       references point_of_sale (id) 
       on delete cascade

    alter table summary_for_fabric 
       add constraint FKaidap5vh0xnxhcpt7on2rejv1 
       foreign key (order_for_fabric_id) 
       references order_for_fabric (id)

    alter table summary_for_fabric 
       add constraint FK3hs7lqka2xla26gs00i2lgl5x 
       foreign key (point_id) 
       references point_of_sale (id)

    alter table summary_for_fabric 
       add constraint FKmxjd0ux4aup9xaa6fg29cwx8j 
       foreign key (product_id) 
       references product_for_individual (id)

    alter table summary_for_individual 
       add constraint FKsa7lsunaeuqrnfcs0s2ks6shb 
       foreign key (person_id) 
       references individual_person (id)

    alter table summary_for_individual 
       add constraint FKbxo0c5rfndx3a1eqnjb7v68ql 
       foreign key (order_for_individual) 
       references order_for_individual (id)

    alter table summary_for_individual 
       add constraint FKrna067yeg1bh5wssp7qy0au5s 
       foreign key (product_id) 
       references product_for_individual (id)

    alter table summary_for_legal 
       add constraint FKkkw6bvaa8ccx90rxtoc74xr63 
       foreign key (person_id) 
       references legal_person (id)

    alter table summary_for_legal 
       add constraint FKjxbhg0jy0qe2v8kkwtb7s5cdh 
       foreign key (order_legal_id) 
       references order_for_legal (id)

    alter table summary_for_legal 
       add constraint FKf8xu7e70iuel8l86pccrqufou 
       foreign key (product_id) 
       references product_for_legal (id)