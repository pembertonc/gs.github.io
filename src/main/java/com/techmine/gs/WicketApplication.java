package com.techmine.gs;

import com.techmine.gs.ui.pages.IndexPage.IndexPage;
import com.techmine.gs.ui.pages.unauthenticated_base_page.UnAuthenticatedBasePage;

import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.naming.InitialContext;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.bean.validation.BeanValidationConfiguration;
import org.apache.wicket.cdi.CdiConfiguration;
import org.apache.wicket.cdi.ConversationPropagation;
import org.apache.wicket.csp.CSPDirective;
import org.apache.wicket.csp.CSPDirectiveSrcValue;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.https.HttpsConfig;
import org.apache.wicket.protocol.https.HttpsMapper;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 *
 * @see com.techmine-inc.Start#main(String[])
 */
public class WicketApplication extends AuthenticatedWebApplication {

    @Inject
    BeanManager beanManager;

    // for testing convience  To be removed
    WicketApplication(BeanManager beanManager) {
        this.beanManager = beanManager;
    }

    // automatice generation default no-argument constructor blocked by argument constuctor.
    public WicketApplication() {
    }

    /**
     * @return @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return IndexPage.class;

    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
// set the error page for expired page
        getApplicationSettings().setPageExpiredErrorPage(IndexPage.class);

        // needed to prevent password in clear text.
        setRootRequestMapper(new HttpsMapper(getRootRequestMapper(),
                new HttpsConfig(8080, 8181)));

        // needed for the styling used by the quickstart
        getCspSettings().blocking()
                .add(CSPDirective.STYLE_SRC, CSPDirectiveSrcValue.SELF)
                .add(CSPDirective.STYLE_SRC, "https://fonts.googleapis.com/css")
                .add(CSPDirective.FONT_SRC, "https://fonts.gstatic.com");

        // needed for CSS in GS
        getCspSettings().blocking()
                .add(CSPDirective.STYLE_SRC, "https://www.w3schools.com/w3css/4/w3.css")
                .add(CSPDirective.STYLE_SRC, "https://fonts.googleapis.com/icon?family=Material+Icons");

        // add your configuration here
        // support for CDI  .
        beanManager = getBeanManager();
        new CdiConfiguration().setFallbackBeanManager(beanManager)
                .setPropagation(ConversationPropagation.NONE)
                .configure(this);
        // support for vean validation.
        new BeanValidationConfiguration().configure(this);

    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return CustomAuthenticatedWebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return IndexPage.class;
    }

    public static BeanManager getBeanManager() {
        try {
            return CDI.current().getBeanManager();
        } catch (Exception ignore) {
        }
        try {
            return (BeanManager) InitialContext.doLookup("java:comp/BeanManager");
        } catch (Exception ignore) {
        }
        try {
            return (BeanManager) InitialContext.doLookup("java:comp/env/BeanManager");
        } catch (Exception ignore) {
        }

        return null;
    }
}
