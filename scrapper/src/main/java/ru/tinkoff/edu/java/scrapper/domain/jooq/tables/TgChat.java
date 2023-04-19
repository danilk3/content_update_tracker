/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.domain.jooq.tables;


import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
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
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.TgChatRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TgChat extends TableImpl<TgChatRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.tg_chat</code>
     */
    public static final TgChat TG_CHAT = new TgChat();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TgChatRecord> getRecordType() {
        return TgChatRecord.class;
    }

    /**
     * The column <code>public.tg_chat.chat_id</code>.
     */
    public final TableField<TgChatRecord, Long> CHAT_ID = createField(DSL.name("chat_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.tg_chat.created_at</code>.
     */
    public final TableField<TgChatRecord, OffsetDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(DSL.field("now()", SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    private TgChat(Name alias, Table<TgChatRecord> aliased) {
        this(alias, aliased, null);
    }

    private TgChat(Name alias, Table<TgChatRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.tg_chat</code> table reference
     */
    public TgChat(String alias) {
        this(DSL.name(alias), TG_CHAT);
    }

    /**
     * Create an aliased <code>public.tg_chat</code> table reference
     */
    public TgChat(Name alias) {
        this(alias, TG_CHAT);
    }

    /**
     * Create a <code>public.tg_chat</code> table reference
     */
    public TgChat() {
        this(DSL.name("tg_chat"), null);
    }

    public <O extends Record> TgChat(Table<O> child, ForeignKey<O, TgChatRecord> key) {
        super(child, key, TG_CHAT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<TgChatRecord> getPrimaryKey() {
        return Keys.CHAT_ID;
    }

    @Override
    public TgChat as(String alias) {
        return new TgChat(DSL.name(alias), this);
    }

    @Override
    public TgChat as(Name alias) {
        return new TgChat(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TgChat rename(String name) {
        return new TgChat(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TgChat rename(Name name) {
        return new TgChat(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, OffsetDateTime> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
