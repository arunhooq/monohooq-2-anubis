function getApiGatewayHost({
  includeProtocol = false,
  includeVersion = false,
  customApiVersion = null
} = {}) {
  const apiEnv =
    process.env.API_GATEWAY_ENV === 'prod'
      ? ''
      : `-${process.env.API_GATEWAY_ENV}`;

  // default to API Gateway version 2.0
  const apiVer = customApiVersion || process.env.API_GATEWAY_VERSION_2_0;

  return `${includeProtocol ? 'https://' : ''}api${apiEnv}.hooq.tv${
    includeVersion ? `/${apiVer}` : ''
  }`;
}

function getApiHost({ includeProtocol = false, includeVersion = false } = {}) {
  // Use VPC for Sanctuary API so requests do not go over Internet. However
  // VPC is only accessible by web servers in production environment using internal DNS.
  // We should use .tv domain extension for testing in local, nightly, preprod environment.
  const isProdEnv = process.env.APP_ENV === 'production';
  const apiEnv = process.env.SANCTUARY_API_ENV;
  const apiVer = process.env.SANCTUARY_API_VERSION;

  const version = includeVersion ? `/${apiVer}` : '';
  const domainExtension = isProdEnv ? 'vpc' : 'tv';

  let protocol = '';
  if (includeProtocol) {
    // VPC does not have HTTPS support
    protocol = isProdEnv ? 'http://' : 'https://';
  }

  return `${protocol}api-${apiEnv}.hooq.${domainExtension}${version}`;
}

function getServerPath(
  path,
  { isApiGateway = false, customApiVersion = null } = {}
) {
  if (path) {
    const getHost = isApiGateway ? getApiGatewayHost : getApiHost;
    const serverPath = getHost({
      includeProtocol: true,
      includeVersion: true,
      customApiVersion
    });

    return `${serverPath}${path}`;
  }

  return null;
}

module.exports = {
  getApiGatewayHost,
  getServerPath
};
