import FullStoryVersion from './full-story-version';
import FullStoryComment from './full-story-comment';
import Status from './status';

export default class FullStory {
    id: number;
    title: string;
    intro: string;
    text: string;
    status: Status;
    lastUpdatedBy: string;
    users: Array<string>;
    likes: number;
    dislikes: number;
    storyVersions: Array<FullStoryVersion>;
    comments: Array<FullStoryComment>;
}
