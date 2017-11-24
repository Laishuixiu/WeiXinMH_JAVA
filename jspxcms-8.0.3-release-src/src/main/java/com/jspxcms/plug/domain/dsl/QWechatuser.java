package com.jspxcms.plug.domain.dsl;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.jspxcms.plug.domain.Wechatuser;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QWechatuser extends EntityPathBase<Wechatuser> {

	private static final long serialVersionUID = 1834474490L;

	private static final PathInits INITS = PathInits.DIRECT2;

	public static final QWechatuser wechatuser = new QWechatuser("wechatuser");

	public final StringPath nickname = createString("nickname");

	public final com.jspxcms.core.domain.dsl.QSite site;

	public QWechatuser(String variable) {
		this(Wechatuser.class, forVariable(variable), INITS);
	}

	public QWechatuser(Path<? extends Wechatuser> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
	}

	public QWechatuser(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QWechatuser(PathMetadata metadata, PathInits inits) {
		this(Wechatuser.class, metadata, inits);
	}

	public QWechatuser(Class<? extends Wechatuser> type, PathMetadata metadata, PathInits inits) {
		super(type, metadata, inits);
		this.site = inits.isInitialized("site") ? new com.jspxcms.core.domain.dsl.QSite(forProperty("site"), inits.get("site")): null;
	}

}
