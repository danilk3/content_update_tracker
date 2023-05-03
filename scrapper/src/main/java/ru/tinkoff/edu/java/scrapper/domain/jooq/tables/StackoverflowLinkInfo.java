/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.domain.jooq.tables;


import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row10;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import ru.tinkoff.edu.java.scrapper.domain.jooq.Keys;
import ru.tinkoff.edu.java.scrapper.domain.jooq.Public;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.StackoverflowLinkInfoRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class StackoverflowLinkInfo extends TableImpl<StackoverflowLinkInfoRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.stackoverflow_link_info</code>
     */
    public static final StackoverflowLinkInfo STACKOVERFLOW_LINK_INFO = new StackoverflowLinkInfo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StackoverflowLinkInfoRecord> getRecordType() {
        return StackoverflowLinkInfoRecord.class;
    }

    /**
     * The column <code>public.stackoverflow_link_info.id</code>.
     */
    public final TableField<StackoverflowLinkInfoRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.stackoverflow_link_info.question_id</code>.
     */
    public final TableField<StackoverflowLinkInfoRecord, Long> QUESTION_ID = createField(DSL.name("question_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.stackoverflow_link_info.link_id</code>.
     */
    public final TableField<StackoverflowLinkInfoRecord, Long> LINK_ID = createField(DSL.name("link_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.stackoverflow_link_info.tags</code>.
     */
    public final TableField<StackoverflowLinkInfoRecord, String[]> TAGS = createField(DSL.name("tags"), SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>public.stackoverflow_link_info.is_answered</code>.
     */
    public final TableField<StackoverflowLinkInfoRecord, Boolean> IS_ANSWERED = createField(DSL.name("is_answered"), SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>public.stackoverflow_link_info.view_count</code>.
     */
    public final TableField<StackoverflowLinkInfoRecord, Long> VIEW_COUNT = createField(DSL.name("view_count"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.stackoverflow_link_info.answer_count</code>.
     */
    public final TableField<StackoverflowLinkInfoRecord, Long> ANSWER_COUNT = createField(DSL.name("answer_count"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.stackoverflow_link_info.score</code>.
     */
    public final TableField<StackoverflowLinkInfoRecord, Integer> SCORE = createField(DSL.name("score"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.stackoverflow_link_info.creation_date</code>.
     */
    public final TableField<StackoverflowLinkInfoRecord, OffsetDateTime> CREATION_DATE = createField(DSL.name("creation_date"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "");

    /**
     * The column <code>public.stackoverflow_link_info.title</code>.
     */
    public final TableField<StackoverflowLinkInfoRecord, String> TITLE = createField(DSL.name("title"), SQLDataType.CLOB.nullable(false), this, "");

    private StackoverflowLinkInfo(Name alias, Table<StackoverflowLinkInfoRecord> aliased) {
        this(alias, aliased, null);
    }

    private StackoverflowLinkInfo(Name alias, Table<StackoverflowLinkInfoRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.stackoverflow_link_info</code> table
     * reference
     */
    public StackoverflowLinkInfo(String alias) {
        this(DSL.name(alias), STACKOVERFLOW_LINK_INFO);
    }

    /**
     * Create an aliased <code>public.stackoverflow_link_info</code> table
     * reference
     */
    public StackoverflowLinkInfo(Name alias) {
        this(alias, STACKOVERFLOW_LINK_INFO);
    }

    /**
     * Create a <code>public.stackoverflow_link_info</code> table reference
     */
    public StackoverflowLinkInfo() {
        this(DSL.name("stackoverflow_link_info"), null);
    }

    public <O extends Record> StackoverflowLinkInfo(Table<O> child, ForeignKey<O, StackoverflowLinkInfoRecord> key) {
        super(child, key, STACKOVERFLOW_LINK_INFO);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<StackoverflowLinkInfoRecord, Integer> getIdentity() {
        return (Identity<StackoverflowLinkInfoRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<StackoverflowLinkInfoRecord> getPrimaryKey() {
        return Keys.STACKOVERFLOW_LINK_INFO_ID;
    }

    @Override
    public List<ForeignKey<StackoverflowLinkInfoRecord, ?>> getReferences() {
        return Arrays.asList(Keys.STACKOVERFLOW_LINK_INFO__FK_LINK);
    }

    private transient Link _link;

    /**
     * Get the implicit join path to the <code>public.link</code> table.
     */
    public Link link() {
        if (_link == null)
            _link = new Link(this, Keys.STACKOVERFLOW_LINK_INFO__FK_LINK);

        return _link;
    }

    @Override
    public StackoverflowLinkInfo as(String alias) {
        return new StackoverflowLinkInfo(DSL.name(alias), this);
    }

    @Override
    public StackoverflowLinkInfo as(Name alias) {
        return new StackoverflowLinkInfo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public StackoverflowLinkInfo rename(String name) {
        return new StackoverflowLinkInfo(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public StackoverflowLinkInfo rename(Name name) {
        return new StackoverflowLinkInfo(name, null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<Integer, Long, Long, String[], Boolean, Long, Long, Integer, OffsetDateTime, String> fieldsRow() {
        return (Row10) super.fieldsRow();
    }
}