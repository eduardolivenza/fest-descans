import * as React from "react";
import { NewDesignLayout } from "layout";

import { TeamSection, WorkSection } from "pods/landing";

export const DefaultPage = () => (
  <>
   {/*
    <AppLayout>
     <DefaultContainer/>
      <LandingPage />
    </AppLayout>
    */}
    <NewDesignLayout>
      <TeamSection />
      <WorkSection />
    </NewDesignLayout>
  </>
);
