<?php

namespace JWTexample\Silex\Provider\Controller;

use Silex\Application;
use Silex\Api\ControllerProviderInterface;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;

class AuthControllerProvider implements ControllerProviderInterface
{
    public function connect(Application $app)
    {
        // creates a new controller based on the default route
        $controllers = $app['controllers_factory'];

        $controllers->post('/authenticate', function (Request $request) use ($app) {

          $post = array(
            'username'    => $request->request->get('username'),
            'password'    => $request->request->get('password'));

            //  echo "user: ".$post['username']." pass: ".$post['password'];

            try {
                   if (empty($post['username']) || empty($post['password'])) {
                       throw new UsernameNotFoundException(sprintf('Username "%s" does not exist.', $post['username']));
                   }



                   $user = $app['users']->loadUserByUsername($post['username']);

                   if (! $app['security.default_encoder']->isPasswordValid($user->getPassword(), $post['password'], '')) {
                       throw new UsernameNotFoundException(sprintf('Username "%s" does not exist.', $post['username']));
                   } else {
                       $response = [
                           'success'  => true,
                           'username' => $user->getUsername(),
                           'token'    => $app['security.jwt.encoder']->encode(['name' => $user->getUsername()]),
                       ];
                   }
               } catch (UsernameNotFoundException $e) {
                   $response = [
                       'success' => false,
                       'error' => 'Invalid credentials',
                   ];
               }

               return $app->json($response, ($response['success'] == true ? Response::HTTP_OK : Response::HTTP_BAD_REQUEST));
           });


        return $controllers;
    }
}

 ?>
