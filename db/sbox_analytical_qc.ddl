-- DROP SCHEMA sbox_analytical_qc;

CREATE SCHEMA sbox_analytical_qc AUTHORIZATION app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.amenability_predictions_id_seq;

CREATE SEQUENCE sbox_analytical_qc.amenability_predictions_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.amenability_predictions_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.amenability_predictions_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.calls_id_seq;

CREATE SEQUENCE sbox_analytical_qc.calls_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.calls_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.calls_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.experiment_grades_id_seq;

CREATE SEQUENCE sbox_analytical_qc.experiment_grades_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.experiment_grades_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.experiment_grades_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.experiment_grades_id_seq1;

CREATE SEQUENCE sbox_analytical_qc.experiment_grades_id_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.experiment_grades_id_seq1 OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.experiment_grades_id_seq1 TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.experiments_id_seq;

CREATE SEQUENCE sbox_analytical_qc.experiments_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.experiments_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.experiments_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.files_id_seq;

CREATE SEQUENCE sbox_analytical_qc.files_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.files_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.files_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.flags_id_seq;

CREATE SEQUENCE sbox_analytical_qc.flags_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.flags_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.flags_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.grades_id_seq;

CREATE SEQUENCE sbox_analytical_qc.grades_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.grades_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.grades_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.list_substances_id_seq;

CREATE SEQUENCE sbox_analytical_qc.list_substances_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.list_substances_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.list_substances_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.lists_id_seq;

CREATE SEQUENCE sbox_analytical_qc.lists_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.lists_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.lists_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.nmr_amen_flags_id_seq;

CREATE SEQUENCE sbox_analytical_qc.nmr_amen_flags_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.nmr_amen_flags_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.nmr_amen_flags_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.sample_grades_id_seq;

CREATE SEQUENCE sbox_analytical_qc.sample_grades_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.sample_grades_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.sample_grades_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.samples_id_seq;

CREATE SEQUENCE sbox_analytical_qc.samples_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.samples_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.samples_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.substance_files_id_seq;

CREATE SEQUENCE sbox_analytical_qc.substance_files_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.substance_files_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.substance_files_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.substance_flags_id_seq;

CREATE SEQUENCE sbox_analytical_qc.substance_flags_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.substance_flags_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.substance_flags_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.substance_grades_id_seq;

CREATE SEQUENCE sbox_analytical_qc.substance_grades_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.substance_grades_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.substance_grades_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.substance_predicted_properties_id_seq;

CREATE SEQUENCE sbox_analytical_qc.substance_predicted_properties_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.substance_predicted_properties_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.substance_predicted_properties_id_seq TO app_qsar;

-- DROP SEQUENCE sbox_analytical_qc.substances_id_seq;

CREATE SEQUENCE sbox_analytical_qc.substances_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE sbox_analytical_qc.substances_id_seq OWNER TO app_qsar;
GRANT ALL ON SEQUENCE sbox_analytical_qc.substances_id_seq TO app_qsar;
-- sbox_analytical_qc.calls definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.calls;

CREATE TABLE sbox_analytical_qc.calls (
	id serial4 NOT NULL,
	"name" varchar(3) NOT NULL,
	description varchar(255) NOT NULL,
	CONSTRAINT calls_call_key UNIQUE (name),
	CONSTRAINT calls_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.calls OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.calls TO app_qsar;


-- sbox_analytical_qc.files definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.files;

CREATE TABLE sbox_analytical_qc.files (
	id serial4 NOT NULL,
	file_name varchar(255) NULL,
	file_date date NULL,
	CONSTRAINT files_file_name_key UNIQUE (file_name),
	CONSTRAINT files_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.files OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.files TO app_qsar;


-- sbox_analytical_qc.grades definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.grades;

CREATE TABLE sbox_analytical_qc.grades (
	id serial4 NOT NULL,
	"name" varchar(3) NOT NULL,
	description varchar(255) NOT NULL,
	CONSTRAINT grades_grade_key UNIQUE (name),
	CONSTRAINT grades_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.grades OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.grades TO app_qsar;


-- sbox_analytical_qc.lists definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.lists;

CREATE TABLE sbox_analytical_qc.lists (
	id serial4 NOT NULL,
	"name" varchar(31) NOT NULL,
	description varchar(255) NULL,
	hidden bool NULL,
	CONSTRAINT lists_name_key UNIQUE (name),
	CONSTRAINT lists_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.lists OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.lists TO app_qsar;


-- sbox_analytical_qc.nmr_amen_flags definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.nmr_amen_flags;

CREATE TABLE sbox_analytical_qc.nmr_amen_flags (
	id serial4 NOT NULL,
	"name" varchar(31) NOT NULL,
	description varchar(255) NULL,
	CONSTRAINT nmr_amen_flags_name_key UNIQUE (name),
	CONSTRAINT nmr_amen_flags_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.nmr_amen_flags OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.nmr_amen_flags TO app_qsar;


-- sbox_analytical_qc.structure_flags definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.structure_flags;

CREATE TABLE sbox_analytical_qc.structure_flags (
	id int4 NOT NULL DEFAULT nextval('sbox_analytical_qc.flags_id_seq'::regclass),
	"name" varchar(31) NOT NULL,
	description varchar(255) NULL,
	CONSTRAINT flags_flag_key UNIQUE (name),
	CONSTRAINT flags_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.structure_flags OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.structure_flags TO app_qsar;


-- sbox_analytical_qc.substances definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.substances;

CREATE TABLE sbox_analytical_qc.substances (
	id serial4 NOT NULL,
	dtxsid varchar(40) NOT NULL,
	preferred_name varchar(255) NOT NULL,
	casrn varchar(40) NOT NULL,
	mol_formula varchar(255) NULL,
	mol_weight float8 NULL,
	pubchem_cid int8 NULL,
	CONSTRAINT substances_casrn_key UNIQUE (casrn),
	CONSTRAINT substances_dtxsid_key UNIQUE (dtxsid),
	CONSTRAINT substances_pkey PRIMARY KEY (id),
	CONSTRAINT substances_preferred_name_key UNIQUE (preferred_name)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.substances OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.substances TO app_qsar;


-- sbox_analytical_qc.amenability_predictions definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.amenability_predictions;

CREATE TABLE sbox_analytical_qc.amenability_predictions (
	id serial4 NOT NULL,
	substance_id int4 NOT NULL,
	lcms_amen_neg float8 NULL,
	lcms_amen_pos float8 NULL,
	nmr_amen_flag_id int4 NULL,
	lcms_amen_neg_true bool NULL,
	lcms_amen_pos_true bool NULL,
	CONSTRAINT amenability_predictions_pkey PRIMARY KEY (id),
	CONSTRAINT amenability_predictions_substance_id_key UNIQUE (substance_id),
	CONSTRAINT amenability_predictions_substance_id_key1 UNIQUE (substance_id),
	CONSTRAINT amenability_predictions_nmr_amen_flag_id_fkey FOREIGN KEY (nmr_amen_flag_id) REFERENCES sbox_analytical_qc.nmr_amen_flags(id),
	CONSTRAINT amenability_predictions_substance_id_fkey FOREIGN KEY (substance_id) REFERENCES sbox_analytical_qc.substances(id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.amenability_predictions OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.amenability_predictions TO app_qsar;


-- sbox_analytical_qc.list_substances definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.list_substances;

CREATE TABLE sbox_analytical_qc.list_substances (
	id serial4 NOT NULL,
	list_id int4 NOT NULL,
	substance_id int4 NOT NULL,
	CONSTRAINT list_substances_list_id_substance_id_key UNIQUE (list_id, substance_id),
	CONSTRAINT list_substances_pkey PRIMARY KEY (id),
	CONSTRAINT list_substances_list_id_fkey FOREIGN KEY (list_id) REFERENCES sbox_analytical_qc.lists(id) ON DELETE CASCADE,
	CONSTRAINT list_substances_substance_id_fkey FOREIGN KEY (substance_id) REFERENCES sbox_analytical_qc.substances(id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.list_substances OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.list_substances TO app_qsar;


-- sbox_analytical_qc.property_predictions definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.property_predictions;

CREATE TABLE sbox_analytical_qc.property_predictions (
	id int4 NOT NULL DEFAULT nextval('sbox_analytical_qc.substance_predicted_properties_id_seq'::regclass),
	substance_id int4 NOT NULL,
	bp_degc float8 NULL,
	mp_degc float8 NULL,
	vp_mmhg float8 NULL,
	logp float8 NULL,
	CONSTRAINT property_predictions_substance_id_key UNIQUE (substance_id),
	CONSTRAINT substance_predicted_properties_pkey PRIMARY KEY (id),
	CONSTRAINT substance_predicted_properties_substance_id_fkey FOREIGN KEY (substance_id) REFERENCES sbox_analytical_qc.substances(id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.property_predictions OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.property_predictions TO app_qsar;


-- sbox_analytical_qc.samples definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.samples;

CREATE TABLE sbox_analytical_qc.samples (
	id serial4 NOT NULL,
	substance_id int4 NULL,
	ncgc_id varchar(40) NULL,
	tox21_id int8 NULL,
	pubchem_sid int8 NULL,
	bottle_barcode varchar(40) NULL,
	withdrawn bool NOT NULL,
	CONSTRAINT samples_ncgc_id_key UNIQUE (ncgc_id),
	CONSTRAINT samples_pkey PRIMARY KEY (id),
	CONSTRAINT samples_pubchem_sid_key UNIQUE (pubchem_sid),
	CONSTRAINT samples_tox21_id_key UNIQUE (tox21_id),
	CONSTRAINT samples_substance_id_fkey FOREIGN KEY (substance_id) REFERENCES sbox_analytical_qc.substances(id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.samples OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.samples TO app_qsar;


-- sbox_analytical_qc.substance_annotations definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.substance_annotations;

CREATE TABLE sbox_analytical_qc.substance_annotations (
	id int4 NOT NULL DEFAULT nextval('sbox_analytical_qc.substance_grades_id_seq'::regclass),
	substance_id int4 NOT NULL,
	t0_grade_id int4 NULL,
	validated bool NOT NULL,
	t4_grade_id int4 NULL,
	call_id int4 NULL,
	annotation text NULL,
	CONSTRAINT substance_grades_pkey PRIMARY KEY (id),
	CONSTRAINT substance_grades_call_id_fkey FOREIGN KEY (call_id) REFERENCES sbox_analytical_qc.calls(id),
	CONSTRAINT substance_grades_grade_id_fkey FOREIGN KEY (t0_grade_id) REFERENCES sbox_analytical_qc.grades(id),
	CONSTRAINT substance_grades_substance_id_fkey FOREIGN KEY (substance_id) REFERENCES sbox_analytical_qc.substances(id),
	CONSTRAINT substance_grades_t4_grade_id_fkey FOREIGN KEY (t4_grade_id) REFERENCES sbox_analytical_qc.grades(id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.substance_annotations OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.substance_annotations TO app_qsar;


-- sbox_analytical_qc.substance_files definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.substance_files;

CREATE TABLE sbox_analytical_qc.substance_files (
	id serial4 NOT NULL,
	substance_id int4 NOT NULL,
	file_id int4 NOT NULL,
	note varchar(255) NULL,
	CONSTRAINT substance_files_pkey PRIMARY KEY (id),
	CONSTRAINT substance_files_substance_id_file_id_key UNIQUE (substance_id, file_id),
	CONSTRAINT substance_files_file_id_fkey FOREIGN KEY (file_id) REFERENCES sbox_analytical_qc.files(id),
	CONSTRAINT substance_files_substance_id_fkey FOREIGN KEY (substance_id) REFERENCES sbox_analytical_qc.substances(id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.substance_files OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.substance_files TO app_qsar;


-- sbox_analytical_qc.substance_structure_flags definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.substance_structure_flags;

CREATE TABLE sbox_analytical_qc.substance_structure_flags (
	id int4 NOT NULL DEFAULT nextval('sbox_analytical_qc.substance_flags_id_seq'::regclass),
	substance_id int4 NOT NULL,
	structure_flag_id int4 NOT NULL,
	validated bool NOT NULL,
	CONSTRAINT substance_flags_pkey PRIMARY KEY (id),
	CONSTRAINT substance_flags_substance_id_flag_id_key UNIQUE (substance_id, structure_flag_id),
	CONSTRAINT substance_flags_flag_id_fkey FOREIGN KEY (structure_flag_id) REFERENCES sbox_analytical_qc.structure_flags(id),
	CONSTRAINT substance_flags_substance_id_fkey FOREIGN KEY (substance_id) REFERENCES sbox_analytical_qc.substances(id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.substance_structure_flags OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.substance_structure_flags TO app_qsar;


-- sbox_analytical_qc.experiments definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.experiments;

CREATE TABLE sbox_analytical_qc.experiments (
	id serial4 NOT NULL,
	file_id int4 NOT NULL,
	sample_id int4 NOT NULL,
	experiment_date date NULL,
	study varchar(255) NULL,
	timepoint bpchar(2) NULL,
	ms_detector varchar(31) NULL,
	plate int4 NULL,
	batch varchar(31) NULL,
	well varchar(15) NULL,
	"row" bpchar(1) NULL,
	col int4 NULL,
	grade varchar(31) NULL,
	purity varchar(127) NULL,
	purity_fraction float8 NULL,
	expected_conc float8 NULL,
	nmr_conc float8 NULL,
	measured_conc float8 NULL,
	pass_fail varchar(127) NULL,
	pass_fail_backup varchar(127) NULL,
	"comment" varchar(127) NULL,
	notes varchar(255) NULL,
	orig_id int4 NULL,
	orig_files_id int4 NOT NULL,
	orig_files_content_id int4 NULL,
	is_tripod int4 NULL,
	CONSTRAINT experiments_pkey PRIMARY KEY (id),
	CONSTRAINT experiments_file_id_fkey FOREIGN KEY (file_id) REFERENCES sbox_analytical_qc.files(id),
	CONSTRAINT experiments_sample_id_fkey FOREIGN KEY (sample_id) REFERENCES sbox_analytical_qc.samples(id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.experiments OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.experiments TO app_qsar;


-- sbox_analytical_qc.sample_annotations definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.sample_annotations;

CREATE TABLE sbox_analytical_qc.sample_annotations (
	id int4 NOT NULL DEFAULT nextval('sbox_analytical_qc.sample_grades_id_seq'::regclass),
	sample_id int4 NOT NULL,
	t0_grade_id int4 NULL,
	validated bool NOT NULL,
	t4_grade_id int4 NULL,
	call_id int4 NULL,
	annotation text NULL,
	CONSTRAINT sample_grades_pkey PRIMARY KEY (id),
	CONSTRAINT sample_grades_call_id_fkey FOREIGN KEY (call_id) REFERENCES sbox_analytical_qc.calls(id),
	CONSTRAINT sample_grades_grade_id_fkey FOREIGN KEY (t0_grade_id) REFERENCES sbox_analytical_qc.grades(id),
	CONSTRAINT sample_grades_sample_id_fkey FOREIGN KEY (sample_id) REFERENCES sbox_analytical_qc.samples(id),
	CONSTRAINT sample_grades_t4_grade_id_fkey FOREIGN KEY (t4_grade_id) REFERENCES sbox_analytical_qc.grades(id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.sample_annotations OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.sample_annotations TO app_qsar;


-- sbox_analytical_qc.experiment_annotations definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.experiment_annotations;

CREATE TABLE sbox_analytical_qc.experiment_annotations (
	id int4 NOT NULL DEFAULT nextval('sbox_analytical_qc.experiment_grades_id_seq'::regclass),
	experiment_id int4 NOT NULL,
	annotation text NULL,
	CONSTRAINT experiment_annotations_experiment_id_key UNIQUE (experiment_id),
	CONSTRAINT experiment_grades_pkey PRIMARY KEY (id),
	CONSTRAINT experiment_grades_experiment_id_fkey FOREIGN KEY (experiment_id) REFERENCES sbox_analytical_qc.experiments(id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.experiment_annotations OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.experiment_annotations TO app_qsar;


-- sbox_analytical_qc.experiment_grades definition

-- Drop table

-- DROP TABLE sbox_analytical_qc.experiment_grades;

CREATE TABLE sbox_analytical_qc.experiment_grades (
	id serial4 NOT NULL,
	experiment_id int4 NOT NULL,
	grade_id int4 NOT NULL,
	CONSTRAINT experiment_grades_experiment_id_grade_id_key UNIQUE (experiment_id, grade_id),
	CONSTRAINT experiment_grades_pkey1 PRIMARY KEY (id),
	CONSTRAINT experiment_grades_experiment_id_fkey1 FOREIGN KEY (experiment_id) REFERENCES sbox_analytical_qc.experiments(id),
	CONSTRAINT experiment_grades_grade_id_fkey1 FOREIGN KEY (grade_id) REFERENCES sbox_analytical_qc.grades(id)
);

-- Permissions

ALTER TABLE sbox_analytical_qc.experiment_grades OWNER TO app_qsar;
GRANT ALL ON TABLE sbox_analytical_qc.experiment_grades TO app_qsar;




-- Permissions

GRANT ALL ON SCHEMA sbox_analytical_qc TO app_qsar;
