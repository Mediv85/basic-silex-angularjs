<?php

namespace JWTexample\Silex\Provider\Controller;

use Silex\Application;
use Silex\Api\ControllerProviderInterface;
use Symfony\Component\HttpFoundation\Request;

class TellMeASecretControllerProvider implements ControllerProviderInterface
{
    public function connect(Application $app)
    {
        // creates a new controller based on the default route
        $controllers = $app['controllers_factory'];


        $controllers->get('/tellmeasecret', function () use ($app) {

          $ret=array('responce' =>  'You are my father!!!');

          return $app->json($ret);
        });


        return $controllers;
    }
}
