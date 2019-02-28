package com.sctaylor.example.application.dagger.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by simon on 6/27/2018.
 */

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ToodooApplicationScope {

}
