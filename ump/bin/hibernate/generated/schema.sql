
    drop table bid_division_sup_rel cascade constraints;

    drop table bid_divisiton cascade constraints;

    drop table bid_divisiton_cust cascade constraints;

    drop table bid_fees_sup_rel cascade constraints;

    drop table bid_item_sup_rel cascade constraints;

    drop table bid_ledg_atta_rel cascade constraints;

    drop table bid_ledger cascade constraints;

    drop table bid_measure_sup_rel cascade constraints;

    drop table bid_measure_sup_rel2 cascade constraints;

    drop table bid_open_record cascade constraints;

    drop table bid_open_record_sup cascade constraints;

    drop table bid_other_item_rel cascade constraints;

    drop table bid_project cascade constraints;

    drop table bid_sporadic_sup_rel cascade constraints;

    drop table bid_sup cascade constraints;

    drop table bid_sup_attach_rel cascade constraints;

    drop table bid_sup_other_rel cascade constraints;

    drop table bid_taxs_sup_rel cascade constraints;

    drop table bid_tech_item cascade constraints;

    create table bid_division_sup_rel (
        bid_division_sup_rel_id varchar2(50 char) not null unique,
        batch_no number(19,0),
        comp_unit_amt varchar2(20 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        item_name varchar2(100 char),
        item_no varchar2(50 char),
        quantitie number(18,6),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        row_no number(19,0),
        short_item_no varchar2(12 char),
        total_amt varchar2(20 char),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_divisiton_id varchar2(50 char),
        bid_project_id varchar2(50 char),
        bid_sup_id varchar2(50 char) not null,
        primary key (bid_division_sup_rel_id)
    );

    create table bid_divisiton (
        bid_divisiton_id varchar2(50 char) not null unique,
        batch_no number(19,0),
        comp_unit_price_amt varchar2(20 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        item_desc varchar2(500 char),
        item_name varchar2(100 char),
        item_no varchar2(50 char),
        quantitie varchar2(20 char),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        row_no number(19,0),
        short_item_no varchar2(12 char),
        total_amt varchar2(20 char),
        unit_desc varchar2(10 char),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_project_id varchar2(50 char) not null,
        primary key (bid_divisiton_id)
    );

    create table bid_divisiton_cust (
        bid_divisiton_cust_id varchar2(50 char) not null unique,
        batch_no number(19,0),
        comp_unit_amt varchar2(20 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        item_desc varchar2(500 char),
        item_name varchar2(100 char),
        item_no varchar2(50 char),
        quantitie varchar2(20 char),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        row_no number(19,0),
        total_amt varchar2(20 char),
        unit_desc varchar2(10 char),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_project_id varchar2(50 char) not null,
        bid_sup_id varchar2(50 char) not null,
        primary key (bid_divisiton_cust_id)
    );

    create table bid_fees_sup_rel (
        bid_fees_sup_rel_id varchar2(50 char) not null unique,
        amt varchar2(20 char),
        batch_no number(19,0),
        calc_basic_desc varchar2(100 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        item_name varchar2(100 char),
        item_no varchar2(50 char),
        rate varchar2(20 char),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        row_no number(19,0),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_project_id varchar2(50 char) not null,
        bid_sup_id varchar2(50 char) not null,
        primary key (bid_fees_sup_rel_id)
    );

    create table bid_item_sup_rel (
        bid_item_sup_rel_id varchar2(50 char) not null unique,
        content_desc varchar2(1000 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        score number(10,3),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_sup_id varchar2(50 char) not null,
        bid_tech_item_id varchar2(50 char) not null,
        primary key (bid_item_sup_rel_id)
    );

    create table bid_ledg_atta_rel (
        bid_ledg_atta_rel_id varchar2(50 char) not null unique,
        batch_no number(18,0),
        bid_atta_eval1_flg varchar2(1 char),
        bid_atta_eval2_flg varchar2(1 char),
        bid_atta_eval3_flg varchar2(1 char),
        bid_atta_eval4_flg varchar2(1 char),
        bid_atta_eval5_flg varchar2(1 char),
        bid_atta_eval6_flg varchar2(1 char),
        bid_atta_eval7_flg varchar2(1 char),
        bid_atta_eval9_flg varchar2(1 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_dept_cd varchar2(20 char),
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_dept_cd varchar2(20 char),
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_ledger_id varchar2(50 char) not null,
        primary key (bid_ledg_atta_rel_id)
    );

    create table bid_ledger (
        bid_ledger_id varchar2(50 char) not null unique,
        atta_draw_file_flg varchar2(1 char),
        atta_list_flg varchar2(1 char),
        atta_tech_stan_flg varchar2(1 char),
        atta_tend_file_flg varchar2(1 char),
        batch_no number(19,0),
        bid_area_desc varchar2(500 char),
        bid_confirm_plan_date timestamp,
        bid_confirm_real_date timestamp,
        bid_cont_attach_id varchar2(50 char),
        bid_desc varchar2(1000 char),
        bid_mode_cd varchar2(1 char),
        bid_open_plan_date timestamp,
        bid_open_real_date timestamp,
        bid_res_id varchar2(50 char),
        bid_section_name varchar2(100 char),
        bid_status_cd varchar2(1 char),
        biz_area number(10,0),
        budget_in_flg varchar2(1 char),
        budget_out_flg varchar2(1 char),
        calc_mode_cd varchar2(1 char),
        call_res_attach_id varchar2(50 char),
        call_res_id varchar2(50 char),
        ccbp_id varchar2(50 char),
        ccbp_no varchar2(50 char),
        construction varchar2(200 char),
        consult_flg varchar2(1 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        end_date timestamp,
        follow_cds varchar2(100 char),
        follow_names varchar2(100 char),
        guarantee_amt number(18,0),
        house_area number(10,0),
        judge_cd varchar2(50 char),
        judge_date timestamp,
        judge_name varchar2(100 char),
        last_guar_date timestamp,
        last_rece_date timestamp,
        need_guar_flg varchar2(1 char),
        oper_desc varchar2(200 char),
        org_cd varchar2(20 char),
        org_name varchar2(100 char),
        overall_type_flg varchar2(1 char),
        pay_type_desc varchar2(200 char),
        period_num varchar2(20 char),
        place_desc varchar2(1000 char),
        proj_intr_desc varchar2(2000 char),
        quan_demand_desc varchar2(500 char),
        rank_date_num number(19,0),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        res_approve_cd varchar2(100 char),
        res_approve_info_id varchar2(50 char),
        section_no varchar2(50 char),
        section_total_area number(10,0),
        serial_no number(19,0),
        serial_type varchar2(50 char),
        src_type_cd varchar2(20 char),
        start_date timestamp,
        target_amt number(18,0),
        total_rank_desc varchar2(500 char),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        visable_flg varchar2(1 char),
        primary key (bid_ledger_id)
    );

    create table bid_measure_sup_rel (
        bid_measure_sup_rel_id varchar2(50 char) not null unique,
        amt varchar2(20 char),
        batch_no number(19,0),
        calc_basic_desc varchar2(100 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        item_name varchar2(100 char),
        item_no varchar2(50 char),
        rate varchar2(20 char),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        row_no number(19,0),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_project_id varchar2(50 char) not null,
        bid_sup_id varchar2(50 char) not null,
        primary key (bid_measure_sup_rel_id)
    );

    create table bid_measure_sup_rel2 (
        bid_measure_sup_rel2_id varchar2(50 char) not null unique,
        batch_no number(19,0),
        calc_basic_desc varchar2(100 char),
        comp_unit_amt varchar2(20 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        item_name varchar2(100 char),
        item_no varchar2(50 char),
        quantitie varchar2(20 char),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        row_no number(19,0),
        total_amt varchar2(20 char),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_project_id varchar2(50 char) not null,
        bid_sup_id varchar2(50 char) not null,
        primary key (bid_measure_sup_rel2_id)
    );

    create table bid_open_record (
        bid_open_record_id varchar2(50 char) not null unique,
        base_price number(18,0),
        batch_no number(19,0),
        bid_section_name varchar2(50 char),
        built_area number(18,0),
        construction varchar2(50 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_dept_cd varchar2(20 char),
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        invite_sup_num number(19,0),
        item_no varchar2(50 char),
        last_rece_date timestamp,
        open_date timestamp,
        open_dep1 varchar2(20 char),
        open_dep2 varchar2(20 char),
        open_dep3 varchar2(20 char),
        open_dep4 varchar2(20 char),
        open_dep5 varchar2(20 char),
        open_dep6 varchar2(20 char),
        open_dep7 varchar2(20 char),
        openner1 varchar2(20 char),
        openner2 varchar2(20 char),
        openner3 varchar2(20 char),
        openner4 varchar2(20 char),
        openner5 varchar2(20 char),
        openner6 varchar2(20 char),
        openner7 varchar2(20 char),
        project_cost number(18,0),
        receive_sup_num number(19,0),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        sign varchar2(20 char),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_dept_cd varchar2(20 char),
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        version_no varchar2(50 char),
        bid_ledger_id varchar2(50 char) not null,
        primary key (bid_open_record_id)
    );

    create table bid_open_record_sup (
        bid_open_record_sup_id varchar2(50 char) not null unique,
        content_desc varchar2(200 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_dept_cd varchar2(20 char),
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        difference number(18,0),
        percent varchar2(20 char),
        pre_priece number(18,0),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        sequence number(19,0),
        sup_name varchar2(200 char),
        time_limit varchar2(20 char),
        total_price number(18,0),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_dept_cd varchar2(20 char),
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_open_record_id varchar2(50 char) not null,
        bid_sup_id varchar2(50 char) not null,
        primary key (bid_open_record_sup_id)
    );

    create table bid_other_item_rel (
        bid_other_item_rel_id varchar2(50 char) not null unique,
        amt varchar2(20 char),
        batch_no number(19,0),
        calc_basic_desc varchar2(100 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        item_name varchar2(100 char),
        item_no varchar2(50 char),
        quantitie varchar2(20 char),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        row_no number(19,0),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_project_id varchar2(50 char) not null,
        bid_sup_id varchar2(50 char) not null,
        primary key (bid_other_item_rel_id)
    );

    create table bid_project (
        bid_project_id varchar2(50 char) not null unique,
        attach_flg varchar2(1 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        delete_flg varchar2(1 char),
        disp_order_no number(19,0),
        project_desc varchar2(1000 char),
        project_name varchar2(100 char),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_ledger_id varchar2(50 char) not null,
        primary key (bid_project_id)
    );

    create table bid_sporadic_sup_rel (
        bid_sporadic_sup_rel_id varchar2(50 char) not null unique,
        basic_desc varchar2(100 char),
        batch_no number(19,0),
        comp_unit_amt varchar2(20 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        item_name varchar2(100 char),
        item_no varchar2(50 char),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        row_no number(19,0),
        temp_quantitie varchar2(20 char),
        total_amt varchar2(20 char),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_project_id varchar2(50 char) not null,
        bid_sup_id varchar2(50 char) not null,
        primary key (bid_sporadic_sup_rel_id)
    );

    create table bid_sup (
        bid_sup_id varchar2(50 char) not null unique,
        attach_flg varchar2(1 char),
        bid_date timestamp,
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        disp_order_no number(19,0),
        email varchar2(100 char),
        guar_atta_conf_date timestamp,
        guar_atta_conf_flg varchar2(1 char),
        guar_atta_conf_name varchar2(100 char),
        guar_atta_conf_uiid varchar2(50 char),
        link_desc varchar2(200 char),
        link_man varchar2(100 char),
        open_placct_cd varchar2(50 char),
        open_placct_flg varchar2(1 char),
        ori_pwd varchar2(50 char),
        provide_level_cd varchar2(20 char),
        receive_date timestamp,
        receive_status_cd varchar2(1 char),
        record_version number(19,0) not null,
        refund_date timestamp,
        refund_status_cd varchar2(1 char),
        rel_sup_basic_id varchar2(50 char),
        remark varchar2(200 char),
        submit_attach_flg varchar2(1 char),
        submit_date timestamp,
        submit_status_cd varchar2(1 char),
        sup_bid_status_cd varchar2(1 char),
        sup_name varchar2(100 char),
        tech_attach_date timestamp,
        tech_attach_status_cd varchar2(1 char),
        type_cd varchar2(1 char),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_ledger_id varchar2(50 char) not null,
        primary key (bid_sup_id)
    );

    create table bid_sup_attach_rel (
        bid_sup_attach_rel_id varchar2(50 char) not null unique,
        atta_biz_flg varchar2(1 char),
        atta_tech_flg varchar2(1 char),
        batch_no varchar2(10 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_dept_cd varchar2(20 char),
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        total_price number(18,0),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_dept_cd varchar2(20 char),
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_sup_id varchar2(50 char) not null,
        primary key (bid_sup_attach_rel_id)
    );

    create table bid_sup_other_rel (
        bidsupotherrel_id varchar2(50 char) not null unique,
        batch_no number(19,0),
        content_desc varchar2(1000 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        fee_amt varchar2(20 char),
        fee_name varchar2(100 char),
        item_no varchar2(50 char),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        row_no number(19,0),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_sup_id varchar2(50 char) not null,
        primary key (bidsupotherrel_id)
    );

    create table bid_taxs_sup_rel (
        bid_taxs_sup_rel_id varchar2(50 char) not null unique,
        amt varchar2(20 char),
        batch_no number(19,0),
        calc_basic_desc varchar2(100 char),
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        item_name varchar2(100 char),
        item_no varchar2(50 char),
        rate varchar2(20 char),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        row_no number(19,0),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_project_id varchar2(50 char) not null,
        bid_sup_id varchar2(50 char) not null,
        primary key (bid_taxs_sup_rel_id)
    );

    create table bid_tech_item (
        bid_tech_item_id varchar2(50 char) not null unique,
        created_center_cd varchar2(20 char),
        created_date timestamp,
        created_position_cd varchar2(20 char),
        creator varchar2(20 char),
        disp_order_no number(19,0),
        item_cd varchar2(10 char),
        item_name varchar2(200 char),
        module_name varchar2(100 char),
        record_version number(19,0) not null,
        remark varchar2(200 char),
        updated_center_cd varchar2(20 char),
        updated_date timestamp,
        updated_position_cd varchar2(20 char),
        updator varchar2(20 char),
        bid_ledger_id varchar2(50 char) not null,
        primary key (bid_tech_item_id)
    );

    alter table bid_division_sup_rel 
        add constraint FKECA28898918344 
        foreign key (bid_divisiton_id) 
        references bid_divisiton;

    alter table bid_division_sup_rel 
        add constraint FKECA2889832AA6224 
        foreign key (bid_sup_id) 
        references bid_sup;

    alter table bid_division_sup_rel 
        add constraint FKECA28898CEA58544 
        foreign key (bid_project_id) 
        references bid_project;

    alter table bid_divisiton 
        add constraint FKE8851783CEA58544 
        foreign key (bid_project_id) 
        references bid_project;

    alter table bid_divisiton_cust 
        add constraint FKC705A18F32AA6224 
        foreign key (bid_sup_id) 
        references bid_sup;

    alter table bid_divisiton_cust 
        add constraint FKC705A18FCEA58544 
        foreign key (bid_project_id) 
        references bid_project;

    alter table bid_fees_sup_rel 
        add constraint FKDACC0E9832AA6224 
        foreign key (bid_sup_id) 
        references bid_sup;

    alter table bid_fees_sup_rel 
        add constraint FKDACC0E98CEA58544 
        foreign key (bid_project_id) 
        references bid_project;

    alter table bid_item_sup_rel 
        add constraint FK31ACDDFE32AA6224 
        foreign key (bid_sup_id) 
        references bid_sup;

    alter table bid_item_sup_rel 
        add constraint FK31ACDDFEF4E23B41 
        foreign key (bid_tech_item_id) 
        references bid_tech_item;

    alter table bid_ledg_atta_rel 
        add constraint FK919DA71BFB9509B0 
        foreign key (bid_ledger_id) 
        references bid_ledger;

    alter table bid_measure_sup_rel 
        add constraint FK381F160532AA6224 
        foreign key (bid_sup_id) 
        references bid_sup;

    alter table bid_measure_sup_rel 
        add constraint FK381F1605CEA58544 
        foreign key (bid_project_id) 
        references bid_project;

    alter table bid_measure_sup_rel2 
        add constraint FKCBC3AACD32AA6224 
        foreign key (bid_sup_id) 
        references bid_sup;

    alter table bid_measure_sup_rel2 
        add constraint FKCBC3AACDCEA58544 
        foreign key (bid_project_id) 
        references bid_project;

    alter table bid_open_record 
        add constraint FKAD3C3A4FB9509B0 
        foreign key (bid_ledger_id) 
        references bid_ledger;

    alter table bid_open_record_sup 
        add constraint FK2E6E3F331859EC29 
        foreign key (bid_open_record_id) 
        references bid_open_record;

    alter table bid_open_record_sup 
        add constraint FK2E6E3F3332AA6224 
        foreign key (bid_sup_id) 
        references bid_sup;

    alter table bid_other_item_rel 
        add constraint FKC028FD3E32AA6224 
        foreign key (bid_sup_id) 
        references bid_sup;

    alter table bid_other_item_rel 
        add constraint FKC028FD3ECEA58544 
        foreign key (bid_project_id) 
        references bid_project;

    alter table bid_project 
        add constraint FK968721D7FB9509B0 
        foreign key (bid_ledger_id) 
        references bid_ledger;

    alter table bid_sporadic_sup_rel 
        add constraint FK431A9D6832AA6224 
        foreign key (bid_sup_id) 
        references bid_sup;

    alter table bid_sporadic_sup_rel 
        add constraint FK431A9D68CEA58544 
        foreign key (bid_project_id) 
        references bid_project;

    alter table bid_sup 
        add constraint FKF8FDA40CFB9509B0 
        foreign key (bid_ledger_id) 
        references bid_ledger;

    alter table bid_sup_attach_rel 
        add constraint FK6CF71DB232AA6224 
        foreign key (bid_sup_id) 
        references bid_sup;

    alter table bid_sup_other_rel 
        add constraint FK51435E7732AA6224 
        foreign key (bid_sup_id) 
        references bid_sup;

    alter table bid_taxs_sup_rel 
        add constraint FK1463B41332AA6224 
        foreign key (bid_sup_id) 
        references bid_sup;

    alter table bid_taxs_sup_rel 
        add constraint FK1463B413CEA58544 
        foreign key (bid_project_id) 
        references bid_project;

    alter table bid_tech_item 
        add constraint FK9D623E5AFB9509B0 
        foreign key (bid_ledger_id) 
        references bid_ledger;
