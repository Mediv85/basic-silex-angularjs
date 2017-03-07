<?php

namespace JWTexample\Silex\Provider\Security;

use Symfony\Component\HttpFoundation\RequestMatcherInterface;
use Symfony\Component\HttpFoundation\Request;

class PreflightRequestMatcher implements RequestMatcherInterface
{
    public function matches(Request $request)
    {
        return $this->isPreflightRequest($request);
    }

    private function isPreflightRequest(Request $request)
    {
        return $request->getMethod() === "OPTIONS" && $request->headers->has("Access-Control-Request-Method");
    }
}
