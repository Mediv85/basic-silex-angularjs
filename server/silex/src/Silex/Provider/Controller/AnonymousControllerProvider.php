<?php

namespace JWTexample\Silex\Provider\Controller;

use Silex\Application;
use Silex\Api\ControllerProviderInterface;
use Symfony\Component\HttpFoundation\Response;

class AnonymousControllerProvider implements ControllerProviderInterface
{
    public function connect(Application $app)
    {
        // creates a new controller based on the default route
        $controllers = $app['controllers_factory'];

        //anonimus calls
        $controllers->get('/greetings', function () use ($app) {

          $ret=array('responce'  =>  'Hey!!! How is going???');

          return $app->json($ret);
        });

        return $controllers;
    }
}


 ?>
