<?php
namespace Silex\Tests;


use Silex\WebTestCase;
use JWTexample\Silex\ApplicationJWT;
//use Symfony\Component\Security\Core\Exception\UsernameNotFoundException;

class AuthControllerTest extends WebTestCase
{
    public function createApplication()
    {
        $app = new ApplicationJWT();

        $app['debug'] = true;
        unset($app['exception_handler']);
        return $app;

    }

    public function testAuthSuccess()
    {

          $client = $this->createClient();

          $params = array(
            'username'    => 'admin',
            'password'    => 'admin');

          $crawler = $client->request(
                                    'POST',
                                    '/auth/authenticate',
                                    array(),
                                    array(),
                                    array('CONTENT_TYPE'  => 'application/json'),
                                    json_encode($params)
                                  );

          $this->assertTrue($client->getResponse()->isOk());

          //decode json responce
          $content=json_decode($client->getResponse()->getContent(), true);

          $token = $this->app['security.jwt.encoder']->encode(['name' => $params['username']]);

          $this->assertTrue($content['success']);
          $this->assertContains($token,$content['token']);
          $this->assertContains('admin',$content['username']);

    }

    public function testAuthFail()
    {

          $client = $this->createClient();

          $params = array(
            'username'    => 'admin',
            'password'    => 'wrongpass');

            //TODO manage exception
            /*  $crawler = $client->request(
                                    'POST',
                                    '/auth/authenticate',
                                    array(),
                                    array(),
                                    array('CONTENT_TYPE'  => 'application/json'),
                                    json_encode($params)
                                  );
*/




    }
}

?>
