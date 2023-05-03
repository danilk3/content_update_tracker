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
import org.jooq.Row9;
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
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.GithubLinkInfoRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class GithubLinkInfo extends TableImpl<GithubLinkInfoRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.github_link_info</code>
     */
    public static final GithubLinkInfo GITHUB_LINK_INFO = new GithubLinkInfo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<GithubLinkInfoRecord> getRecordType() {
        return GithubLinkInfoRecord.class;
    }

    /**
     * The column <code>public.github_link_info.id</code>.
     */
    public final TableField<GithubLinkInfoRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.github_link_info.link_id</code>.
     */
    public final TableField<GithubLinkInfoRecord, Long> LINK_ID = createField(DSL.name("link_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.github_link_info.name</code>.
     */
    public final TableField<GithubLinkInfoRecord, String> NAME = createField(DSL.name("name"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.github_link_info.owner_name</code>.
     */
    public final TableField<GithubLinkInfoRecord, String> OWNER_NAME = createField(DSL.name("owner_name"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.github_link_info.fork</code>.
     */
    public final TableField<GithubLinkInfoRecord, Boolean> FORK = createField(DSL.name("fork"), SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>public.github_link_info.forks_count</code>.
     */
    public final TableField<GithubLinkInfoRecord, Long> FORKS_COUNT = createField(DSL.name("forks_count"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.github_link_info.watchers_count</code>.
     */
    public final TableField<GithubLinkInfoRecord, Long> WATCHERS_COUNT = createField(DSL.name("watchers_count"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.github_link_info.pushed_at</code>.
     */
    public final TableField<GithubLinkInfoRecord, OffsetDateTime> PUSHED_AT = createField(DSL.name("pushed_at"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.github_link_info.created_at</code>.
     */
    public final TableField<GithubLinkInfoRecord, OffsetDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "");

    private GithubLinkInfo(Name alias, Table<GithubLinkInfoRecord> aliased) {
        this(alias, aliased, null);
    }

    private GithubLinkInfo(Name alias, Table<GithubLinkInfoRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.github_link_info</code> table reference
     */
    public GithubLinkInfo(String alias) {
        this(DSL.name(alias), GITHUB_LINK_INFO);
    }

    /**
     * Create an aliased <code>public.github_link_info</code> table reference
     */
    public GithubLinkInfo(Name alias) {
        this(alias, GITHUB_LINK_INFO);
    }

    /**
     * Create a <code>public.github_link_info</code> table reference
     */
    public GithubLinkInfo() {
        this(DSL.name("github_link_info"), null);
    }

    public <O extends Record> GithubLinkInfo(Table<O> child, ForeignKey<O, GithubLinkInfoRecord> key) {
        super(child, key, GITHUB_LINK_INFO);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<GithubLinkInfoRecord, Integer> getIdentity() {
        return (Identity<GithubLinkInfoRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<GithubLinkInfoRecord> getPrimaryKey() {
        return Keys.GITHUB_LINK_INFO_ID;
    }

    @Override
    public List<ForeignKey<GithubLinkInfoRecord, ?>> getReferences() {
        return Arrays.asList(Keys.GITHUB_LINK_INFO__FK_LINK);
    }

    private transient Link _link;

    /**
     * Get the implicit join path to the <code>public.link</code> table.
     */
    public Link link() {
        if (_link == null)
            _link = new Link(this, Keys.GITHUB_LINK_INFO__FK_LINK);

        return _link;
    }

    @Override
    public GithubLinkInfo as(String alias) {
        return new GithubLinkInfo(DSL.name(alias), this);
    }

    @Override
    public GithubLinkInfo as(Name alias) {
        return new GithubLinkInfo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public GithubLinkInfo rename(String name) {
        return new GithubLinkInfo(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public GithubLinkInfo rename(Name name) {
        return new GithubLinkInfo(name, null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<Integer, Long, String, String, Boolean, Long, Long, OffsetDateTime, OffsetDateTime> fieldsRow() {
        return (Row9) super.fieldsRow();
    }
}