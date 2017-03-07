<?php

namespace JWTexample\Silex;

use Silex\Application;
use Symfony\Component\HttpFoundation\Request;

use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\RequestMatcher;

use Silex\Provider\SecurityServiceProvider;
use Silex\Provider\SecurityJWTServiceProvider;
use Silex\Provider\DoctrineServiceProvider;
use Silex\Provider\MonologServiceProvider;
use JDesrosiers\Silex\Provider\CorsServiceProvider;

use JWTexample\Silex\Provider\Controller\AuthControllerProvider;
use JWTexample\Silex\Provider\Controller\AnonymousControllerProvider;
use JWTexample\Silex\Provider\Controller\TellMeASecretControllerProvider;

use JWTexample\Silex\Provider\User\UserProvider;

use JWTexample\Silex\Provider\Security\PreflightRequestMatcher;

class ApplicationJWT extends Application
{
    public function __construct(array $values = [])
    {
        parent::__construct($values);


        $this['debug'] = true;

        //set Monolog
        $this->register(new MonologServiceProvider(), array(
            'monolog.logfile' => __DIR__.'/../development.log',
        ));


        $json_config=json_decode(file_get_contents(__DIR__.'/../settings.json'),true);



        //set CORS filter
        $this->register(new CorsServiceProvider(), array(
          "cors.allowOrigin" => "*",
          "cors.allowMethods" => "GET,PUT,POST,DELETE,OPTIONS",
          "cors.exposeHeaders" => "X-CSRF-Token X-Requested-With Accept Accept-Version Content-Length Content-MD5 Content-Type Date X-Api-Version X-HTTP-Method-Override Origin"
        ));





        //imposto lettura json parameter
        $this->before(function (Request $request) {
          if (0 === strpos($request->headers->get('Content-Type'), 'application/json')) {
            $data = json_decode($request->getContent(), true);
            $request->request->replace(is_array($data) ? $data : array());
          }
        });



        //set database config
        $this->register(new DoctrineServiceProvider(), array(
          'db.options' => array(
            'driver' => 'pdo_mysql',
            'dbhost' => $json_config['db_settings']['dbhost'],
            'dbname' => $json_config['db_settings']['dbname'],
            'user' => $json_config['db_settings']['user'],
            'password' => $json_config['db_settings']['password'],
          ),
        ));




        //JWT service config
        $this['security.jwt'] = [
            'secret_key' => 'Very_secret_key',
            'life_time'  => 86400,
            'algorithm'  => ['HS256'],
            'options'    => [
                'header_name' => 'x-auth-token', // default null, option for usage normal oauth2 header
                'token_prefix' => '',
            ]
        ];

        //config user provider
        $this['users']=new UserProvider($this['db']);


        //Match all the OPTIONS method request
        $app['cors_preflight_request_matcher']= new PreflightRequestMatcher();

        //Silex security config
        $this['security.firewalls'] = array(
            'login' => array(
                'pattern' => 'authenticate|greetings',   //all the path with 'authenticate' o start with 'unsafe/*' are allow without restrinctions
                'anonymous' => true,
            ),

            'options_pass' => array(
              'pattern' => $app['cors_preflight_request_matcher'],  //all the OPTIONS method request are allow
              'anonymous' => true,
            ),
            'secured' => array(
              'pattern' => '^.*$',  //For others path autentications required
              'logout' => array('logout_path' => '/logout'),
              'users' => $this['users'],
              'jwt' => array(
                  'use_forward' => true,
                  'require_previous_session' => false,
                  'stateless' => true,
              )
            ),
        );



        //register security
        $this->register(new SecurityServiceProvider());
        $this->register(new SecurityJWTServiceProvider());



        //Set CONTROLLERS
        $this->mount('/auth', new AuthControllerProvider());
        $this->mount('/secret', new TellMeASecretControllerProvider());
        $this->mount('/anonymous', new AnonymousControllerProvider());


        //enabled CORS autorization defined before
        $this["cors-enabled"]($this);

    }
}



 ?>
