import { get } from 'lodash';
import getConfig from 'next/config';
import Document, { Head, Main, NextScript } from 'next/document';
import {
  generateMinifiedNREUM,
  isNewRelicEnabled
} from './../helpers/newrelicUtil.js';

const { publicRuntimeConfig } = getConfig();

export default class MyDocument extends Document {
  static async getInitialProps(ctx) {
    const initialProps = await Document.getInitialProps(ctx);
    const gtm = get(ctx.query.partnerConfig, 'CustomConfig.constants.gtm', '');

    return {
      ...initialProps,
      gtm
    };
  }

  addHeartbeatBridge() {
    return (
      <script
        dangerouslySetInnerHTML={{
          __html: `function heartbeat({ titleId, length, position }) {window.heartbeat({ titleId, length, position })}`
        }}
      />
    );
  }

  addDefaultFont() {
    return (
      <link
        href="https://fonts.googleapis.com/css?family=Muli:400,800"
        rel="stylesheet"
      />
    );
  }

  addNRScriptTags() {
    const nrBrowserFilePath = publicRuntimeConfig.NEWRELIC_BROWSER_FILE_PATH;
    if (isNewRelicEnabled()) {
      return <script async src={nrBrowserFilePath} />;
    }
  }

  addConvivaCoreSDKScriptTag() {
    const convivaCoreSdkPath = publicRuntimeConfig.CONVIVA_CORE_SDK_PATH;
    return (
      <script
        key="a2edbdfa-0004-48af-a84a-9a5c8ffc79b0-core-sdk"
        src={convivaCoreSdkPath}
      />
    );
  }

  addConvivaHtml5NativeImplementationPathScriptTag() {
    const convivaHtml5NativeImplementationPath =
      publicRuntimeConfig.CONVIVA_HTML5_NATIVE_IMPLEMENTATION_PATH;
    return (
      <script
        key="0ed5df77-b1dd-4ca8-9fda-9c5a31f34447-conviva-html5"
        src={convivaHtml5NativeImplementationPath}
      />
    );
  }

  addConvivaScriptTags() {
    return [
      this.addConvivaCoreSDKScriptTag(),
      this.addConvivaHtml5NativeImplementationPathScriptTag()
    ].map(script => script);
  }

  addNREUMScriptTags() {
    if (isNewRelicEnabled()) {
      return (
        <script
          dangerouslySetInnerHTML={{
            __html: generateMinifiedNREUM()
          }}
        />
      );
    }
  }

  addGTMScriptTags() {
    return (
      <script
        dangerouslySetInnerHTML={{
          __html: `
        (function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
        new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
        j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
        'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
        })(window,document,'script','dataLayer','${this.props.gtm.id}');
        `
        }}
      />
    );
  }

  addGTMNoScriptTags() {
    return (
      <noscript>
        <iframe
          src={`https://www.googletagmanager.com/ns.html?id=${
            this.props.gtm.id
          }`}
          height="0"
          width="0"
          style={{ display: 'none', visibility: 'hidden' }}
        />
      </noscript>
    );
  }

  render() {
    return (
      <html>
        <Head>
          <meta
            name="viewport"
            content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
          />
          <link
            rel="icon"
            type="image/ico"
            href="/static/img/webviewFavicon.ico"
          />

          {this.addNRScriptTags()}
          {this.addDefaultFont()}
          {this.addNREUMScriptTags()}
          {this.addGTMScriptTags()}
          {this.addHeartbeatBridge()}
          {this.addConvivaScriptTags()}
        </Head>
        <body>
          {this.addGTMNoScriptTags()}

          <Main />
          <NextScript />
        </body>
      </html>
    );
  }
}
