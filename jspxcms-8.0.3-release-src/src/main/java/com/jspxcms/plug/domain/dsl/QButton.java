package com.jspxcms.plug.domain.dsl;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.jspxcms.plug.domain.Button;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QButton extends EntityPathBase<Button> {

	private static final long serialVersionUID = 1834474490L;

	private static final PathInits INITS = PathInits.DIRECT2;

	public static final QButton button = new QButton("button");

	public final DateTimePath<java.util.Date> operatingtime = createDateTime("operatingtime", java.util.Date.class);

	public final DateTimePath<java.util.Date> uploadtime = createDateTime("uploadtime", java.util.Date.class);
	
	 public final NumberPath<Integer> id = createNumber("id", Integer.class);

	public final StringPath name = createString("name");

	public final StringPath type = createString("type");

	public final StringPath key = createString("key");

	public final StringPath url = createString("url");

	public final StringPath isdelete = createString("isdelete");

	public final StringPath isupload = createString("isupload");

	public final StringPath editor = createString("editor");

	public final com.jspxcms.core.domain.dsl.QSite site;

	public QButton(String variable) {
		this(Button.class, forVariable(variable), INITS);
	}

	public QButton(Path<? extends Button> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
	}

	public QButton(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QButton(PathMetadata metadata, PathInits inits) {
		this(Button.class, metadata, inits);
	}

	public QButton(Class<? extends Button> type, PathMetadata metadata, PathInits inits) {
		super(type, metadata, inits);
		this.site = inits.isInitialized("site")
				? new com.jspxcms.core.domain.dsl.QSite(forProperty("site"), inits.get("site")) : null;
	}
}
