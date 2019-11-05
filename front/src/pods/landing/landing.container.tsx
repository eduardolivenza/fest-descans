import * as React from "react";
import { NewDesignLayout } from "./landing.component";
import { TeamSection } from "./TeamSection";
import { WorkSection } from "./WorkSection";
import { withRouter, RouteComponentProps } from "react-router-dom";

interface Props extends RouteComponentProps { }

const LandingContainerInner = (props: Props) => {
  return (
    <NewDesignLayout>
      <TeamSection />
      <WorkSection />
    </NewDesignLayout>
  );
};

export const LandingContainer = withRouter(LandingContainerInner);